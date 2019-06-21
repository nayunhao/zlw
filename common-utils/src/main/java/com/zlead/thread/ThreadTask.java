package com.zlead.thread;

/**
 * @author yangting
 * 线程任务
 */
public interface ThreadTask extends Runnable{
	/*
	 * 设置任务
	 */
	public void setName(String name);
	/*
	 * 获取任务
	 */
	public String getName();
	/*
	 * 取消任务
	 */
	public void cancel();
}
