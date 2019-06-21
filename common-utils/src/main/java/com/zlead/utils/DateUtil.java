/*
 * DateUtil.java Copyright Bejing Passion Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间工具
 * 
 * @author yangting
 *         
 */
public class DateUtil {
    /**
     * 按照pattern转换时间为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String date2Text(Date date, String pattern) {
        String result = StringUtils.EMPTY;
        
        if (date == null)
            return result;
        if (StringUtils.isBlank(pattern))
            pattern = "yyyy-MM-dd HH:mm:ss";
            
        DateFormat format = new SimpleDateFormat(pattern);
        result = format.format(date);
        
        return result;
    }
    
    /**
     * 按照pattern转换字符串为日期
     * 
     * @param text
     * @param pattern
     * @return
     */
    public static Date text2date(String text, String pattern) {
        Date result = null;
        
        if (text == null)
            return result;
        if (StringUtils.isBlank(pattern))
            pattern = "yyyy-MM-dd HH:mm:ss";
            
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            result = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        //result = java.sql.Date.valueOf(text); 
        return result;
    }
    
    /**
     * 获取0点时间
     * 
     * @param date
     * @return
     */
    public static Date getDate0(Date date) {
        String zero = " 00:00:00";
        String text = date2Text(date, "yyyy-MM-dd");
        return text2date(text + zero, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 获取24点时间（即第二天0点）
     * 
     * @param date
     * @return
     */
    public static Date getDate24(Date date) {
        Date zeroTime = getDate0(date);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(zeroTime);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }
    
    /**
     * 获取上一天时间
     * 
     * @param date
     * @return
     */
    public static Date getLastDay(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }
    
    /**
     * 获取日期所在年度开始时间（本年度一月一日零时）
     * 
     * @param date
     *            空则取当前时间
     * @return
     */
    public static Date getYearBegin(Date date) {
        Date d = (Date) date.clone();
        if (d == null)
            d = new Date();
        String begin = date2Text(d, "yyyy");
        Date beginTime = text2date(begin + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        return beginTime;
    }
    
    /**
     * 获取日期所在年度结束时间（下一年度一月一日零时）
     * 
     * @param date
     * @return
     */
    public static Date getYearEnd(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(getYearBegin(date));
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }
    /** 
    * 验证签名 
    * @param signature  验证签名的数据
    * @param timestamp  参数
    * @param nonce  参数
    * @return  boolean
    */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
	    //TOKEN//与token 比较
	    String[] arr = new String[] { "TG20190306", timestamp, nonce };
	    // 将token、timestamp、nonce三个参数进行字典排序  
	    Arrays.sort(arr);
	    StringBuilder content = new StringBuilder();
	    for (int i = 0; i < arr.length; i++){
	    	content.append(arr[i]);
	    }
	    MessageDigest md = null; 
	    String tmpStr = null;
	    try {
	    	md = MessageDigest.getInstance("SHA-1");
	    	// 将三个参数字符串拼接成一个字符串进行sha1加密
	    	byte[] digest = md.digest(content.toString().getBytes());
	    	tmpStr = byteToStr(digest);
	    } catch (NoSuchAlgorithmException e) {
	    	e.printStackTrace();
	    }
	    content = null;
	    // 将sha1加密后的字符串可与signature对比  
	    return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

    /** 
    * 将字节数组转换为十六进制字符串 
    *  
    * @param byteArray 
    * @return  String
    */
    private static String byteToStr(byte[] byteArray) {
    	String strDigest = "";
    	for (int i = 0; i < byteArray.length; i++) {
    		strDigest += byteToHexStr(byteArray[i]);
    	}
    	return strDigest;
    }
    /** 
    * 将字节转换为十六进制字符串 
    *  
    * @param mByte 
    * @return  String
    */
    private static String byteToHexStr(byte mByte) {
    	char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    	char[] tempArr = new char[2];
    	tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
    	tempArr[1] = Digit[mByte & 0X0F];
    	String s = new String(tempArr);
    	return s;
    }
}
