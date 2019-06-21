/*
 * ExceptionUtils.java Copyright Bejing Passion Tech Co.,Ltd. All Rights
 * Reserved.
 */
package com.zlead.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.zlead.exception.SystemException;
import com.zlead.global.States;

/**
 * 异常处理工具
 * 
 * @author alanyuan
 *         
 */
public class ExceptionUtils {
    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }
    
    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
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
    
    public static SystemException convertSystemException(Throwable exception) {
        if (exception instanceof SystemException) {
            return (SystemException) exception;
        }
        return new SystemException(exception, States.FAIL);
    }
    
    public static void printStackTrace(Throwable exception) {
        if (!(exception instanceof SystemException)) {
            exception.printStackTrace();
        }
    }
}
