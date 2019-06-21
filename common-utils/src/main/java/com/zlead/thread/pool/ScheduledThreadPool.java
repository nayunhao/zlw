package com.zlead.thread.pool;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zlead.utils.IDGenerator;

/**
 * 定时任务线程池
 * 
 * @author yangting
 *         
 */
public class ScheduledThreadPool extends ScheduledThreadPoolExecutor {
    private static Logger           logger     = LoggerFactory.getLogger(ScheduledThreadPool.class);
    private String                  poolName   = null;
    private ThreadLocal<String>     threadName = new ThreadLocal<String>();
    private final ThreadLocal<Long> startTime  = new ThreadLocal<Long>();
    private final AtomicLong        numTasks   = new AtomicLong();
    private final AtomicLong        totalTime  = new AtomicLong();
    private boolean                 down       = true;
                                               
    public ScheduledThreadPool(int corePoolSize, RejectedExecutionHandler handler) {
        this(null, corePoolSize, handler);
    }
    
    public ScheduledThreadPool(String name, int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
        if (name != null && name.trim().length() > 0)
            this.poolName = name;
        else
            this.poolName = "SCH_TP_" + IDGenerator.generatorID();
        this.down = false;
        logger.info(
                "=> ScheduledThreadPool [{}] initialization success. current-pool-size=[{}], max-pool-size=[{}], core-pool-size=[{}]",
                this.poolName, this.getPoolSize(), this.getMaximumPoolSize(), this.getCorePoolSize());
    }
    
    public synchronized void start() {
        if (!down)
            return;
        down = false;
        if (this.getCorePoolSize() <= 0 || this.getMaximumPoolSize() <= 0) {
            throw new IllegalArgumentException("=> Illegal pool size: CorePool[" + this.getCorePoolSize()
                    + "], MaxPool[" + this.getMaximumPoolSize() + "]");
        }
        
        if (this.poolName == null)
            this.poolName = "TP_" + IDGenerator.generatorID();
            
        LoggingThreadGroup threadGroup = new LoggingThreadGroup(this.poolName);
        
        // 创建线程组，并设置该线程组产生的线程为守护线程
        ThreadGroupFactory tFactory = new ThreadGroupFactory(threadGroup, "GROUP");
        tFactory.createDaemonThreads(true);
        
        this.setThreadFactory(tFactory);
        logger.info("=> ScheduledThreadPool Group {} start. pool-size={}", threadGroup.getName(), this.getPoolSize());
    }
    
    /*
     * (non-Javadoc)
     * @see java.utils.concurrent.ScheduledThreadPoolExecutor#schedule(java.utils.
     * concurrent.Callable, long, java.utils.concurrent.TimeUnit)
     */
    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        // TODO Auto-generated method stub
        return super.schedule(callable, delay, unit);
    }
    
    /*
     * (non-Javadoc)
     * @see java.utils.concurrent.ScheduledThreadPoolExecutor#schedule(java.lang.
     * Runnable , long, java.utils.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        // TODO Auto-generated method stub
        return super.schedule(command, delay, unit);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * java.utils.concurrent.ScheduledThreadPoolExecutor#scheduleAtFixedRate(
     * java.lang.Runnable, long, long, java.utils.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        // TODO Auto-generated method stub
        return super.scheduleAtFixedRate(command, initialDelay, period, unit);
    }
    
    /*
     * (non-Javadoc)
     * @see
     * java.utils.concurrent.ScheduledThreadPoolExecutor#scheduleWithFixedDelay
     * (java.lang.Runnable, long, long, java.utils.concurrent.TimeUnit)
     */
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        // TODO Auto-generated method stub
        return super.scheduleWithFixedDelay(command, initialDelay, delay, unit);
    }
    
    /*
     * (non-Javadoc)
     * @see java.utils.concurrent.ThreadPoolExecutor#remove(java.lang.Runnable)
     */
    @Override
    public boolean remove(Runnable command) {
        // TODO Auto-generated method stub
        return super.remove(command);
    }
    
    /**
     * 执行（零延迟）
     * 
     * @param command
     *            命令（任务）
     *            
     * @see java.util.concurrent.ThreadPoolExecutor#execute(Runnable)
     */
    @Override
    public void execute(Runnable command) {
        super.execute(command);
        
    }
    
    /**
     * 关闭(线程中任务还会继续执行直到正常结束)
     * 
     * @see java.util.concurrent.ThreadPoolExecutor#shutdown()
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
     * @see java.util.concurrent.ThreadPoolExecutor#shutdownNow()
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
