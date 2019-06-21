/*
 * SystemException.java Copyright Bejing Passion Tech Co.,Ltd. All Rights
 * Reserved.
 */
package com.zlead.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

import com.zlead.global.States;

/**
 * 系统异常
 * 
 * @author yangting
 *         
 */
public class SystemException extends RuntimeException {
    
    /**
     * 
     */
    private static final long         serialVersionUID = 4801959191828714311L;
                                                       
    private States                    states;
                                      
    private String                    errorInfo;
                                      
    private Object[]                  formats;
                                      
    private final Map<String, Object> properties       = new TreeMap<String, Object>();
                                                       
    public static SystemException wrap(Throwable exception, States states) {
        if (exception instanceof SystemException) {
            return (SystemException) exception;
        } else {
            return new SystemException(exception.getMessage(), exception, states);
        }
    }

    public static SystemException convertSystemException(Throwable exception) {
        if (exception instanceof SystemException) {
            return (SystemException) exception;
        } else {
            return new SystemException(exception, States.FAIL);
        }
    }
    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Throwable ex, Class<?>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<?> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
    
    public static SystemException getInstance(States states) {
        return getInstance(null, states);
    }
    
    public static SystemException getInstance(String message, States states) {
        return new SystemException(message, states);
    }
    
    public SystemException(States states) {
        this.states = states;
        this.errorInfo = states.getMsg();
    }
    
    public SystemException(States states, String errorInfo) {
        this.states = states;
        this.errorInfo = errorInfo;
    }
    
    public SystemException(String message, States states) {
        this(message, null, states);
    }
    
    public SystemException(Throwable cause, States states) {
        this("", cause, states);
    }
    
    public SystemException(String message, Throwable cause, States states) {
        super(message, cause);
        this.states = states;
        this.errorInfo = states.getMsg();
    }
    
    public States getErrorCode() {
        return states;
    }
    
    public String getErrorInfo() {
        return String.format(errorInfo, this.formats);
    }
    
    public SystemException setErrorCode(States states) {
        this.states = states;
        return this;
    }
    
    public Map<String, Object> getProperties() {
        return properties;
    }
    
    @SuppressWarnings( "unchecked" )
    public <T> T get(String name) {
        return (T) properties.get(name);
    }
    
    public SystemException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }
    
    public SystemException set(Map<?, ?> map) {
        for (Object key : map.keySet()) {
            properties.put(String.valueOf(key), map.get(key));
        }
        return this;
    }
    
    public SystemException formats(Object... formats) {
        this.formats = formats;
        return this;
    }
    
    /**
     * 将ErrorStack转化为String.
     */
    public String getStackTraceAsString() {
        StringWriter stringWriter = new StringWriter();
        this.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    @Override
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            printStackTrace(new PrintWriter(s));
        }
    }
    
    @Override
    public void printStackTrace(PrintWriter s) {
        synchronized (s) {
            if (StringUtils.isNotBlank(this.getMessage())) {
                s.println("\t-------------------------------");
                s.println("\t" + this.getMessage());
            }
            if (!properties.isEmpty()) {
                s.println("\t-------------------------------");
                for (String key : properties.keySet()) {
                    s.println("\t" + key + "=[" + properties.get(key) + "]");
                }
            }
            if (states != null) {
                s.println("\t-------------------------------");
                s.println("\t" + states + ":" + states.getClass().getName());
            }
            s.println("\t-------------------------------");
            StackTraceElement[] trace = getStackTrace();
            for (int i = 0; i < trace.length; i++)
                s.println("\tat " + trace[i]);
                
            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(s);
            }
            s.flush();
        }
    }
    
}
