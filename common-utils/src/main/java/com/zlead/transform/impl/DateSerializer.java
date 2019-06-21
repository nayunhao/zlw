/**
 * 
 */
package com.zlead.transform.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zlead.annotation.BeanTransform;

/**
 * 日期序列化转换
 * @author yangting
 */
public class DateSerializer extends BaseTransform<Date, String> {

	@Override
	public String transform(Date value, BeanTransform transform)
			throws Exception {
		if(value != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");		
			return formatter.format(value);
		}
		return null;
	}

}
