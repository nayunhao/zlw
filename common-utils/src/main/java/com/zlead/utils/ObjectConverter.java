package com.zlead.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ObjectConverter {
	private ObjectConverter() {
		throw new AssertionError();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> convet(List<?> src, Class<T> tc) {
		if(null == src || src.isEmpty())
			return Collections.EMPTY_LIST;
		
		List<T> res = new ArrayList<T>();
		for(Object o : src){
			res.add(convet(o,tc));
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K,V> convet(Map<?,?> src,Class<K> key, Class<V> value,boolean token) {
		if(null == src || src.isEmpty())
			return Collections.EMPTY_MAP;
		
		Map<K,V> res = new HashMap<K,V>();
		Set<K> set = (Set<K>)src.keySet();
		for(Object o : set){
			if(token)
				res.put(convet(o, key), convet(src.get(o),value));
			else
				res.put((K)o, convet(src.get(o),value));
		}
		return res;
	}
	
	public static <T> T convet(Object obj, Class<T> tc) {
		if (null == obj)
			return null;
		if (null == tc)
			return null;

		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();
		if (null == fields)
			return null;

		Field[] tfields = tc.getDeclaredFields();
		if (null == tfields)
			return null;

		T res = null;
		try {
			res = tc.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Field ff : fields) {
			ff.setAccessible(true);

			int modifiers = ff.getModifiers();
			if (Modifier.isFinal(modifiers))
				continue;
			if (Modifier.isStatic(modifiers))
				continue;

			Field f = selectField(tfields, ff);
			if (null == f)
				continue;

			try {
				f.setAccessible(true);
				Object value = ff.get(obj);
				setVal(res, f, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private static void setVal(Object obj, Field field, Object value) {
		if (null == value)
			return;
		
		@SuppressWarnings("rawtypes")
		Class fc = field.getType();

		try {
			if (fc == String.class) {
				@SuppressWarnings("rawtypes")
				Class vc = value.getClass();
				if(vc.isPrimitive())
					field.set(obj, value + "");
				else if(vc == String.class){
					field.set(obj, value);
				}else if(vc == Date.class){
					field.set(obj, Convert.toDateFull((Date)value));
				}else if(vc.isEnum()){
					field.set(obj, Enum.valueOf(vc, value+"").name());
				}
			} else if (fc == int.class || fc == Integer.class) {
				field.setInt(obj, Integer.parseInt(value + ""));
			} else if (fc == boolean.class || fc == Boolean.class) {
				field.setBoolean(obj, Boolean.valueOf(value + ""));
			} else if (fc == long.class || fc == Long.class) {
				field.setLong(obj, Long.parseLong(value + ""));
			} else if (fc == float.class || fc == Float.class) {
				field.setFloat(obj, Float.parseFloat(value + ""));
			} else if (fc == double.class || fc == Double.class) {
				field.set(obj, Double.parseDouble(value + ""));
			} else if (fc == byte.class || fc == Byte.class) {
				field.setByte(obj, Byte.parseByte(value + ""));
			} else if (fc == short.class || fc == Short.class) {
				field.setShort(obj, Short.parseShort(value + ""));
			} else if (fc == char.class || fc == Character.class) {
				field.setChar(obj, (value + "").charAt(0));
			} else if (fc == Date.class) {
				try{
					field.set(obj, value);
				}catch(Exception e){
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try{
						Date d = df.parse(value + "");
						field.set(obj, d);
					}catch(Exception e2){
						//IGNORE
					}
				}
			}else if(fc.isEnum()){
				field.set(obj, Enum.valueOf(fc, value + ""));
			} else {
				field.set(obj, value);
			}
		} catch (Exception e) {
			try {
				field.set(obj, value);
			} catch (Exception e1) {
				//IGNORE
			}
		}
	}

	private static Field selectField(Field[] fs, Field f) {
		f.setAccessible(true);
		for (Field field : fs) {
			if (field.getName().equals(f.getName()))
				return field;
		}
		return null;
	}
}
