/**
 * 
 */
package com.zlead.thread;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 *
 */
public interface ScheduledService extends DaemonService {
	public static final String SERVICE_NAME_PREFIX     = "SCHEDULED_";
	
	/**
	 * 定时计划任务
	 * 
	 * @param callable
	 * @param delay
	 * @param unit
	 * @return
	 */
	public ScheduledFuture<?> scheduleTask(ThreadCallableTask<?> task,
			long delay, TimeUnit unit);
    
    /**
	 * 定时计划任务
	 * 
	 * @param command
	 * @param delay
	 * @param unit
	 * @return
	 */
	public ScheduledFuture<?> scheduleTask(ThreadTask task, long delay,
			TimeUnit unit);
    
    /**
	 * 定时计划任务
	 * 
	 * @param command
	 * @param initialDelay
	 * @param period
	 * @param unit
	 * @return
	 */
	public ScheduledFuture<?> scheduleAtFixedRateTask(ThreadTask task,
			long initialDelay, long period, TimeUnit unit);
    
    /**
	 * 定时计划任务
	 * 
	 * @param command
	 * @param initialDelay
	 * @param delay
	 * @param unit
	 * @return
	 */
	public ScheduledFuture<?> scheduleWithFixedDelayTask(ThreadTask task,
			long initialDelay, long delay, TimeUnit unit);
    
    /**
	 * 轮询定时任务
	 * 
	 * @param timeout
	 * @param timeoutUnit
	 * @param delay
	 * @param delayUnit
	 */
	public void pollScheduleRunnable(long timeout, TimeUnit timeoutUnit,
			long delay, TimeUnit delayUnit);
    
    /**
	 * 轮询定时任务
	 * 
	 * @param timeout
	 * @param timeoutUnit
	 * @param initialDelay
	 * @param period
	 * @param delayUnit
	 */
	public void pollScheduleFixedRateRunnable(long timeout,
			TimeUnit timeoutUnit, long initialDelay, long period,
			TimeUnit delayUnit);
    
    /**
	 * 轮询定时任务
	 * 
	 * @param timeout
	 * @param timeoutUnit
	 * @param initialDelay
	 * @param delay
	 * @param delayUnit
	 */
	public void pollScheduleFixedDelayRunnable(long timeout,
			TimeUnit timeoutUnit, long initialDelay, long delay,
			TimeUnit delayUnit);
    
    /**
	 * 轮询定时任务
	 * 
	 * @param timeout
	 * @param timeoutUnit
	 * @param delay
	 * @param delayUnit
	 * @return
	 */
	public ScheduledFuture<?> pollScheduleCallable(long timeout,
			TimeUnit timeoutUnit, long delay, TimeUnit delayUnit);
}
