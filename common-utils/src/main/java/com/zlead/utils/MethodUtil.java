package com.zlead.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodUtil
{
    private static Logger logger = LoggerFactory.getLogger(MethodUtil.class);
	
	//对象序列化成字节数组
	public static byte[] objectToBinary(final Object obj) {
		
		if (null == obj){
			return null;
		}
		ByteArrayOutputStream baStream = new  ByteArrayOutputStream(); 
		try
		{
		    ObjectOutputStream out = new ObjectOutputStream(baStream);
		    out.writeObject(obj);
		    	    		            
		} catch (IOException e) 
		{    
			throw new RuntimeException("catch error.",e);
			//logger.error(e,e.fillInStackTrace());
		}
		
		return baStream.toByteArray();
	}
	
	//字节数组反序列化成对象
	@SuppressWarnings("unchecked")
	public static <T> T binaryToObject(final byte[] bytes){
		
		if (bytes == null){
			return null;
		}
		
		T obj = null;
		ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
		
		try 
		{
		    ObjectInputStream obin = new ObjectInputStream(bin);
		    obj = (T)obin.readObject();
		    
		}
		catch(Exception e)
		{
		   logger.error("binaryToObject error.",e);
		}

		return obj;
	}
}
