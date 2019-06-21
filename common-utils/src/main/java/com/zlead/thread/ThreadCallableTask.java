/**
 * 
 */
package com.zlead.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author yangting
 * 回调线程任务
 */
public interface ThreadCallableTask<T> extends Callable<T> {
	/*
	 * 设置任务，获取任务
	 */
	public void setName(String task);
	public String getName();
	/*
	 * 设置、获取回调值
	 */
	public <V> void setFuture(Future<V> ft);
	public <V> Future<V> getFuture();
	/*
	 * 取消任务
	 */
	public void cancel();
}
