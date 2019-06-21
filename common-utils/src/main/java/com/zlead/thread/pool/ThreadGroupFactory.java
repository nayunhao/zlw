package com.zlead.thread.pool;

import java.util.concurrent.ThreadFactory;

/**
 * 线程组工厂类
 * 
 * @author Alan
 * @version 1.0
 *          
 */
public class ThreadGroupFactory implements ThreadFactory {
    private ThreadGroup  _group;
    private String       _namePrefix;
    private int          _numThreads;
    private boolean      _createDaemonThreads;
    private final Object _syncLock = new Object();
                                   
    /**
     * 创建一个线程组实例，通过此工厂类创建的所有线程线程将被分配到该线程组
     * 
     * @param group
     *            线程组
     * @param namePrefix
     *            此线程组中线程的名称前缀
     */
    public ThreadGroupFactory(ThreadGroup group, String namePrefix) {
        _group = group;
        _namePrefix = namePrefix;
        _numThreads = 0;
    }
    
    /**
     * 创建一个线程组实例，通过此工厂类创建的所有线程线程将被分配到该线程组
     * 
     * @param namePrefix
     *            此线程组中线程的名称前缀
     */
    public ThreadGroupFactory(String namePrefix) {
        this(Thread.currentThread().getThreadGroup(), namePrefix);
    }
    
    /**
     * 设置为守护线程
     * 
     * @param daemonThreads
     *            <code>true</code> 则此工厂类创建的线程为守护线程
     */
    public void createDaemonThreads(boolean daemonThreads) {
        synchronized (_syncLock) {
            _createDaemonThreads = daemonThreads;
        }
    }
    
    /**
     * 生成一个新线程
     * 
     * @param r
     *            线程任务
     * 
     * @see ThreadFactory#newThread(Runnable)
     */
    @Override
    public Thread newThread(Runnable r) {
        String name;
        boolean daemon;
        
        synchronized (_syncLock) {
            name = _namePrefix + ++_numThreads;
            daemon = _createDaemonThreads;
        }
        
        Thread thread = new Thread(_group, r, name);
        thread.setDaemon(daemon);
        
        return thread;
    }
    
}
