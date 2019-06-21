package com.zlead.thread.pool;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zlead.utils.IDGenerator;

/**
 * 线程池
 * 
 * @author yangting
 * @version 1.0
 * @see
 */
public class ThreadPool extends ThreadPoolExecutor {
    private static Logger           logger     = LoggerFactory.getLogger(ThreadPool.class);
    private String                  poolName   = null;
    private ThreadLocal<String>     threadName = new ThreadLocal<String>();
    private final ThreadLocal<Long> startTime  = new ThreadLocal<Long>();
    private final AtomicLong        numTasks   = new AtomicLong();
    private final AtomicLong        totalTime  = new AtomicLong();
    private boolean                 down       = true;
                                               
    public ThreadPool(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this(null, corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, handler);
    }
    
    public ThreadPool(String name, int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue, handler);
        if (maxPoolSize < 1)
            throw new RuntimeException("=> The size of XThreadPool must gt 1:" + maxPoolSize);
        if (name != null && name.trim().length() > 0)
            this.poolName = name;
        else
            this.poolName = "TP_" + IDGenerator.generatorID();
        this.down = false;
        logger.info(
                "=> ThreadPool [{}] initialization success. current-com.delbei.web.thread.pool-size=[{}], max-com.delbei.web.thread.pool-size=[{}], core-com.delei.web.thread.pool-size=[{}]",
                this.poolName, this.getPoolSize(), this.getMaximumPoolSize(), this.getCorePoolSize());
    }
    
    public synchronized void start() {
        if (!down)
            return;
        down = false;
        if (this.getCorePoolSize() <= 0 || this.getMaximumPoolSize() <= 0) {
            throw new IllegalArgumentException("=> Illegal com.delbei.web.thread.pool size: CorePool["
                    + this.getCorePoolSize() + "], MaxPool[" + this.getMaximumPoolSize() + "]");
        }
        
        if (this.poolName == null)
            this.poolName = "TP_" + IDGenerator.generatorID();
            
        LoggingThreadGroup threadGroup = new LoggingThreadGroup(this.poolName);
        
        // 创建线程组，并设置该线程组产生的线程为守护线程
        ThreadGroupFactory tFactory = new ThreadGroupFactory(threadGroup, "GROUP");
        tFactory.createDaemonThreads(true);
        
        this.setThreadFactory(tFactory);
        logger.info("=> ThreadPool Group {} start. com.delbei.web.thread.pool-size=", threadGroup.getName(),
                this.getPoolSize());
    }
    
    /**
     * 执行
     * 
     * @param command
     *            命令（任务）
     *            
     * @see ThreadPoolExecutor#execute(Runnable)
     */
    @Override
    public void execute(Runnable command) {
        super.execute(command);
        
    }
    
    /*
     * (non-Javadoc)
     * @see
     * java.utils.concurrent.AbstractExecutorService#submit(java.utils.concurrent
     * .Callable)
     */
    @Override
    public <T> Future<T> submit(Callable<T> command) {
        // TODO Auto-generated method stub
        return super.submit(command);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * java.utils.concurrent.AbstractExecutorService#submit(java.lang.Runnable,
     * java.lang.Object)
     */
    @Override
    public <T> Future<T> submit(Runnable command, T result) {
        // TODO Auto-generated method stub
        return super.submit(command, result);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * java.utils.concurrent.AbstractExecutorService#submit(java.lang.Runnable)
     */
    @Override
    public Future<?> submit(Runnable command) {
        // TODO Auto-generated method stub
        return super.submit(command);
    }
    
    /**
     * 关闭(线程中任务还会继续执行直到正常结束)
     * 
     * @see ThreadPoolExecutor#shutdown()
     */
    @Override
    public void shutdown() {
        if (isRunning()) {
            down = true;
            if (!isShutdown())
                super.shutdown();
        }
    }
    
    /**
     * 立刻关闭
     * 
     * @see ThreadPoolExecutor#shutdownNow()
     */
    @Override
    public List<Runnable> shutdownNow() {
        List<Runnable> list = null;
        if (isRunning()) {
            down = true;
            if (!isShutdown())
                list = super.shutdownNow();
        }
        return list;
    }
    
    /**
     * 线程池是否运行
     * 
     * @return
     */
    public synchronized boolean isRunning() {
        if (isShutdown())
            down = true;
        return (!down);
    }
    
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        threadName.set(String.format("%s", t));
        logger.info(String.format("=> Thread [%s]: start [%s]", t, r));
        startTime.set(System.currentTimeMillis());
    }
    
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.currentTimeMillis();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            logger.info(String.format("=> Thread [%s]: end [%s], time=[%d]ms", threadName.get(), r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }
    
    @Override
    protected void terminated() {
        try {
            long taskNum = numTasks.get();
            if (taskNum > 0)
                logger.info(String.format("=> ThreadPool [" + this.poolName + "] terminated: avg time=[%d]ms",
                        totalTime.get() / numTasks.get()));
            else
                logger.info(String.format("=> ThreadPool [" + this.poolName + "] terminated: avg time=[%d]ms", 0));
        } finally {
            super.terminated();
        }
    }
}
