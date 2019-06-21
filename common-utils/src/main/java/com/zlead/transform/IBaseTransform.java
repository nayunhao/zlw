package com.zlead.transform;

import com.zlead.annotation.BeanTransform;

/**
 * @author yangting
 * 转换接口
 * @param T
 * @param M
 */
public interface IBaseTransform<T, M> {

	/*
	 * 转换方法
	 */
	public M transform(T value, BeanTransform transform) throws Exception; 
}
