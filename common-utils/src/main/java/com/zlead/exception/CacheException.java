/**
 * 
 */
package com.zlead.exception;

/**
 * 缓存异常
 * 
 * @author yangting
 *        
 */
public class CacheException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4679438690286162553L;
    
    /**
     * 
     */
    public CacheException() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param message
     */
    public CacheException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param cause
     */
    public CacheException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param message
     * @param cause
     */
    public CacheException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    
}
