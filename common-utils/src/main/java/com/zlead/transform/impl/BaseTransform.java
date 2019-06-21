/**
 * 
 */
package com.zlead.transform.impl;

import com.zlead.annotation.BeanTransform;
import com.zlead.transform.IBaseTransform;

/**
 * @author yangting
 * Bean转换抽象接口
 * @param <T>
 * @param <M>
 */
public abstract class BaseTransform<T, M> implements IBaseTransform<T, M> {

	/* (non-Javadoc)
	 * @see com.delbei.web.common.transform.IBaseTransform#transform(java.lang.Object, com.delbei.web.common.annotation.BeanTransform)
	 */
	@Override
	public abstract M transform(T value, BeanTransform transform) throws Exception;
}
