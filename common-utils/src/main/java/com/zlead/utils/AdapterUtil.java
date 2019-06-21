package com.zlead.utils;

import java.util.Map;

/**
 * 数据类型适配类
 * @author yangting
 */
public class AdapterUtil {

	public static Long valueOfObj(Map<String, Object> map,String key)
	{
		Object obj = map.get(key);
		if(obj == null)
			return 0L;
		Class<?> t = map.get(key).getClass();
		Long lRet = 0L;
		if(t.getName().equals("java.lang.Integer"))
		{
			Integer n = (Integer) map.get(key);
			lRet = Long.valueOf(n.toString());
		}
		else if(t.getName().equals("java.lang.Long"))
		{
			lRet = (Long) map.get(key);
		}
		return lRet;
	}
	@SuppressWarnings("rawtypes")
	public static Long valueOfMap(Map map,String key)
	{
		Object obj = map.get(key);
		if(obj == null)
			return 0L;
		Class<?> t = map.get(key).getClass();
		Long lRet = 0L;
		if(t.getName().equals("java.lang.Integer"))
		{
			Integer n = (Integer) map.get(key);
			lRet = Long.valueOf(n.toString());
		}
		else if(t.getName().equals("java.lang.Long"))
		{
			lRet = (Long) map.get(key);
		}
		return lRet;
	}
	
	public static Integer IntValueOfObj(Map<String, Object> map,String key)
	{
		Object obj = map.get(key);
		if(obj == null)
			return 0;
		Class<?> t = map.get(key).getClass();
		Integer lRet = 0;
		if(t.getName().equals("java.lang.Integer"))
		{
			lRet = (Integer) map.get(key);
		}
		else if(t.getName().equals("java.lang.Long"))
		{
			Long l = (Long) map.get(key);
			lRet = Integer.parseInt(l.toString());
		}
		return lRet;
	}
}
