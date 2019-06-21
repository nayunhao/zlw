package com.zlead.thread;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.zlead.thread.pool.ThreadPool;

/**
 * TaskMonitor的出发点是为了满足将一个任务分成几个子任务来并行完成（使用多线程）.
 * 这样做的目的是让任务执行起来变快一些.当然,如果预计一个任务执行很快的话就没必要使用. TaskMonitor需要结合{@link TaskSlice}
 * 来配合使用. 相当于把一个大任务分成几个小的{@link TaskSlice}(任务片)来并行完成.
 * 使用TaskMonitor可以结合线程池一起使用,否则TaskMonitor将创建新的线程执行.
 * 
 * @author yangting
 * @version 1.0
 * @see TaskSlice
 */
public class TaskMonitor {
    
    // 0:初始;1:成功完成 ;2:异常 ;
    public final static int                STATUS_INIT  = 0;
    public final static int                STATUS_OK    = 1;
    public final static int                STATUS_ERROR = 2;
    //
    private LinkedBlockingQueue<TaskSlice> queue        = new LinkedBlockingQueue<TaskSlice>();
    private ThreadPool                     executor     = null;
    //
    private List<Object>                   result       = new ArrayList<Object>();
                                                        
    private Lock                           lock         = new ReentrantLock();
    private Condition                      execCond     = lock.newCondition();
                                                        
    /**
     * default constructor.
     */
    public TaskMonitor() {
    }
    
    /**
     * @param executor
     */
    public TaskMonitor(ThreadPool executor) {
        this.executor = executor;
    }
    
    /**
     * 向任务控制器中添加需要执行的任务.
     * 
     * @param task
     */
    public void addTask(TaskSlice task) {
        Object tid = task.getId();
        if (tid == null)
            throw new NullPointerException("task.id is null");
            
        queue.add(task);
    }
    
    /**
     * 获取执行结果,其实是一个以同步方式等待多个任务异步执行结果的动作.
     * 
     * @return 返回 0:表示所有任务都顺利完成, n:表示有n个任务有问题.
     */
    public synchronized int execute() {
        execute0();
        while (!isCompleted()) {
            try {
                execCond.await();
            } catch (InterruptedException e) {
                // e.printStackTrace();
            }
        }
        
        return getErrorCount();
    }
    
    /**
     *
     */
    private void execute0() {
        if (queue.size() == 0)
            throw new RuntimeException("monitor has not any task slice");
            
        if (executor == null)
            throw new NullPointerException("ThreadExecutor is Null");
            
        if (executor != null && !executor.isRunning())
            executor.start();
            
        // 分片任务需要完成后统一查结果执行是否正常，所以这里不用队列的poll，而是按照指针顺序一个一个进行。
        for (TaskSlice task : queue) {
            task.setMonitor(this);
            // if (executor == null){
            // executor.getThreadFactory().newThread(task).start();
            // }else {
            executor.execute(task);
            // }
        }
    }
    
    /**
     * 提供给任务修改状态的接口.
     */
    public synchronized void notifyMonitor() {
        execCond.signal();
    }
    
    /**
     * 是否每个任务都结束了.
     * 
     * @return
     */
    private boolean isCompleted() {
        for (TaskSlice task : queue) {
            if (task.getStatus() == STATUS_INIT)
                return false;
        }
        return true;
    }
    
    /**
     * 任务都完成后,看看有没有错误执行的任务.
     * 
     * @return
     */
    private int getErrorCount() {
        int rtn = 0;
        for (TaskSlice task : queue) {
            if (task.getStatus() == STATUS_ERROR)
                rtn++;
        }
        return rtn;
    }
    
    /**
     * 通过任务标识获取任务执行的错误信息,如果没有返回null .
     * 
     * @return
     */
    public String getErrorMessage() {
        StringBuffer sb = new StringBuffer();
        for (TaskSlice task : queue) {
            Throwable cause = task.getCause();
            if (cause != null) {
                sb.append("task[").append(task.getId()).append("] cause:").append(cause.getMessage()).append(";");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * 获取各个子任务的执行结果,结果集中可以没有任何返回结果. 子任务可以没有返回结果.
     * 
     * @return
     */
    public synchronized List<?> getResult() {
        return result;
    }
    
    /**
     * 添加结果到结果集中.
     */
    synchronized void addSliceResult(Object obj) {
        if (obj != null)
            result.add(obj);
    }
    
    /**
     * 所有任务执行完毕,清除所有任务,以并GC回收.
     */
    public final void release() {
        queue.clear();
        result.clear();
    }
}
