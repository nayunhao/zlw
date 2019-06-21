package com.zlead.thread;

/**
 * TaskSlice是一个任务片,结合任务监视器({@link})使用并行完成一个大任务.
 * 
 * @author yangting
 * @version 1.0
 * @see TaskMonitor
 */
public abstract class TaskSlice implements Runnable {
    
    // identifier for task
    protected Object    id      = null;
    // 描述任务执行状态，0:初始;1:成功完成 ;2:异常 ;
    private int         status  = TaskMonitor.STATUS_INIT;
    // 描述任务执行情况
    private Throwable   cause   = null;
    //
    private TaskMonitor monitor = null;
                                
    /**
     * default constructor.
     * 
     * @param tid
     */
    public TaskSlice(Object tid) {
        id = tid;
    }
    
    /**
     * 
     * @return
     */
    public Object getId() {
        return id;
    }
    
    /**
     * 
     * @param monitor
     */
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
    
    public Throwable getCause() {
        return cause;
    }
    
    /**
     * 任务动作.
     */
    @Override
    public final void run() {
        Object result = null;
        try {
            result = execute();
            status = TaskMonitor.STATUS_OK;
            cause = null;
        } catch (Throwable _th) {
            status = TaskMonitor.STATUS_ERROR;
            cause = _th;
        }
        monitor.addSliceResult(result);
        monitor.notifyMonitor();
        monitor = null;
    }
    
    /**
     * 
     * @return
     */
    public final int getStatus() {
        return status;
    }
    
    /**
	 * 任务执行.这个动作是定义每个任务执行的具体细节, 为了保证每个任务的异常需要通知提醒者(Observable),
	 * 最好在实现的时候控制一下任务的异常处理.
	 * 
	 * @throws Throwable
	 */
	public abstract Object execute() throws Throwable;
    
    /**
     * 实现这个方法是为了适应Observable中是以Vector作为存储方式的.
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof TaskSlice))
            return false;
        TaskSlice tobj = (TaskSlice) obj;
        
        if (id == null && tobj.id == null)
            return true;
        if (id != null && id.equals(tobj.id))
            return true;
        else
            return false;
    }
    
    /**
     * Override Object#hashCode() .
     * 
     * @return
     */
    @Override
    public int hashCode() {
        if (id == null)
            return 0;
        else
            return id.hashCode();
    }
}
