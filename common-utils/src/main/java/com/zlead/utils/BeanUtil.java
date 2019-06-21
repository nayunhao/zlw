/*
 * BeanUtil.java Copyright Bejing Passion Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.utils;

import java.lang.reflect.Constructor;

import org.apache.commons.lang3.StringUtils;

/**
 * Bean工具
 * 
 * @author yangting
 *         
 */
public class BeanUtil {
    
    /**
     * 获取Class
     * 
     * @param className
     * @return
     * @throws Exception
     */
    public static Class<?> getClass(String className) throws Exception {
        if (StringUtils.isBlank(className))
            return null;
        Class<?> clazz = Class.forName(className);
        
        return clazz;
    }
    
    public static Object getObject(String className) throws Exception {
        if (StringUtils.isBlank(className))
            return null;
        Class<?> clazz = Class.forName(className);
        if (clazz != null)
            return clazz.newInstance();
        else
            return null;
    }
    
    public static <X> X createBean(Constructor<X> ctor, Object... args) throws Exception {
        if (ctor == null)
            return null;
        X x = null;
        // System.out.println(ctor.toString());
        // System.out.println(args.toString());
        x = ctor.newInstance(args);
        
        return x;
    }
}
