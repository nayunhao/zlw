package com.zlead.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.beanutils.Converter;

import com.zlead.global.GlobalConstants;

/**
 * 时间转换工具
 * 
 * @author alanyuan
 *         
 */
public class DateTimeConverter implements Converter {
    
    /*
     * (non-Javadoc)
     * @see org.apache.commons.beanutils.Converter#convert(java.lang.Class,
     * java.lang.Object)
     */
    @SuppressWarnings( { "rawtypes"} )
    @Override
    public Object convert(Class type, Object value) {
        return toDate(type, value);
    }
    
    @SuppressWarnings( "rawtypes" )
    private static Object toDate(Class type, Object value) {
        if (value == null || "".equals(value))
            return null;
        if (value instanceof String) {
            String dateValue = value.toString().trim();
            int length = dateValue.length();
            if (type.equals(java.util.Date.class)) {
                try {
                    DateFormat formatter = null;
                    if (length <= 10) {
                        formatter = new SimpleDateFormat(GlobalConstants.DATE, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 19) {
                        formatter = new SimpleDateFormat(GlobalConstants.DATETIME, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 23) {
                        formatter = new SimpleDateFormat(GlobalConstants.TIMESTAMP,
                                new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (StringUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
    
    public static String TimeStamp2DateEx(String timestampString, String formats) {
        if (StringUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = Long.parseLong(timestampString);
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }
    /**
     * 取得当前时间戳（精确到毫秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowMsTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time);
        return nowTimeStamp;
    }
    /**
     * 获取今天日期时间戳
     * @return
     */
    public static String getToday()
    {
    	Date date = new Date();  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
    	return sdf.format(date); 
    }
    /**
     * 取系统当前时间
     */
    public static String getNowDate()
    {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
    }
    /**
     * 取得当前时间戳（精确到毫秒）
     *
     * @return nowTimeStamp
     */
    public static long genIdByTime() {
        long time = System.currentTimeMillis();
        return time;
    }
    /**
     * unix时间转为指定格式的字符串
     * @param unixTm
     * @param format "yyyy-MM-dd"
     * @return
     */
    public static String TimeStampToString(Long unixTm, String format)
    {
    	return new java.text.SimpleDateFormat(format).format(new java.util.Date(unixTm * 1000));
    }    
    /**
     * 时间比较 返回值 0:S1=S2相等, <0:S1<S2, >0:S1>S2
     */
    public static int CompareDateTime(String s1, String s2)
    {  
    	java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
    	java.util.Calendar c1=java.util.Calendar.getInstance();     
    	java.util.Calendar c2=java.util.Calendar.getInstance();     
    	try    
    	{     
    		c1.setTime(df.parse(s1));     
    		c2.setTime(df.parse(s2));     
    	}catch(java.text.ParseException e){     
    		System.err.println("格式不正确");     
    	}     
    	
    	return c1.compareTo(c2);   
    }

	/**
	 * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）
	 * @param date
	 * @return
	 */
	public static String getISO8601Timestamp(Date date){
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	    df.setTimeZone(tz);
	    String nowAsISO = df.format(date);
	    return nowAsISO;
	}
}
