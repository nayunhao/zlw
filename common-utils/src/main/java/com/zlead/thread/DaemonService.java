/**
 * 
 */
package com.zlead.thread;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author yangting
 * 
 * 守护服务
 */
public interface DaemonService {
    public static final String SERVICE_NAME_PREFIX     = "DAEMON_";
    public static final String THREAD_NAME_PREFIX      = "THREAD_";
    public static final String THREAD_POOL_NAME_PREFIX = "THREAD_POOL_";
    public static final String TASK_NAME_PREFIX        = "TASK_";
    /*
     * 开始线程,关闭线程
     */
    public void startup();
    public void shutdown();
    /*
     * 设置任务（排队等待轮询）
     */
    public void putTask(ThreadTask task);
    /*
     * 移除任务
     */
    public void removeTask(Runnable task);
    /*
     * 清除任务
     */
    public void purgeTask();
    /*
     * 获取任务数量
     */
    public long getTaskCount();
    /*
     * 提交回调任务
     */
    public Future<?> submitTask(ThreadCallableTask<?> task);
    /*
     * 获取完成任务数
     */
    public long getCompletedTaskCount();
    /*
     * 执行任务（立即执行）
     */
    public void executeTask(ThreadTask task);
    /**
   	 * 轮询任务
   	 * 
   	 * @param timeout
   	 * @param unit
   	 */
    public void pollRunnable(long timeout, TimeUnit unit);
    public Future<?> pollCallable(long timeout, TimeUnit unit);
}
