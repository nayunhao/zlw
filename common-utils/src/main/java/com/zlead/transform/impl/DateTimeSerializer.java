package com.zlead.transform.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zlead.annotation.BeanTransform;

/*
 * 时间序列化转换
 * @Author  yangting
 */
public class DateTimeSerializer extends BaseTransform<Date, String> {

	@Override
	public String transform(Date value, BeanTransform transform)
			throws Exception {
		if(value != null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			return formatter.format(value);
		}
		return "";
	}
}
