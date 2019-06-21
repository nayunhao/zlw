package com.zlead.transform.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.zlead.annotation.BeanTransform;

/**
 * Bean转换
 * @author yangting
 * @param <T>
 * @param <M>
 */
public class BeanSerializer<T, M> extends BaseTransform<T, M> {

	/* (non-Javadoc)
	 * @see com.delbei.web.common.transform.impl.BaseTransform#transform(java.lang.Object, com.delbei.web.common.annotation.BeanTransform)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public M transform(T value, BeanTransform transform) throws Exception {
		if(transform.bean() != NoneTransform.class && value instanceof List<?>)
		{
			List<Object> beanList = new ArrayList<Object>();
			for(Object obj : (List<?>)value)
			{
				Object bean = transform.bean().newInstance();
				BeanUtils.copyProperties(bean, obj);
				beanList.add(bean);
			}
			return (M) beanList;
		}
		return (M) value;
	}

}
