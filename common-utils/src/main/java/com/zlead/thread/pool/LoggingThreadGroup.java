package com.zlead.thread.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 具备日志功能的线程组
 * 
 * @author Alan
 * @version 1.0
 *          
 */
public class LoggingThreadGroup extends ThreadGroup {
    private static final Logger logger = LoggerFactory.getLogger(LoggingThreadGroup.class);
    
    public LoggingThreadGroup(String groupName) {
        super(groupName);
    }
    
    @Override
    public void uncaughtException(Thread t, Throwable exc) {
        logger.warn("=> ThreadGroup[{}]: Unhandled exception", this.getName(), exc);
        super.uncaughtException(t, exc);
    }
}
