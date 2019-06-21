/**
 * 
 */
package com.zlead.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

import com.zlead.transform.impl.BaseTransform;
import com.zlead.transform.impl.DateSerializer;
import com.zlead.transform.impl.NoneTransform;

/**
 * @author yangting
 * 转换Bean注解
 */
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface BeanTransform {
	/*
	 * 使用转换类
	 */
	public Class<? extends BaseTransform<?, ?>> using() default DateSerializer.class;
	/*
	 * bean
	 */
	public Class<?> bean() default NoneTransform.class;
}
