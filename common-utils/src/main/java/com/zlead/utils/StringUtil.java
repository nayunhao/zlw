package com.zlead.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {

	/**
	 * 获取当前时间：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDate() {
		String format = "yyyy-MM-dd HH:mm:ss";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	/**
	 * 获取当前时间：yyyy-MM-dd
	 * @return
	 */
	public static String getDate2() {
		String format = "yyyy-MM-dd";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	/**
	 * 获取当前时间：yyyy
	 * @return
	 */
	public static String getDate_Year() {
		String format = "yyyy";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}


	/**
	 * 获取当前时间：MM
	 * @return
	 */
	public static String getDate_Month() {
		String format = "MM";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	/**
	 * 指定日期字符串天数加1
	 * 
	 * @param dateStr
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public static String getDate3(String dateStr) {
		Calendar dayc1 = new GregorianCalendar();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date daystart = null;
		try {
			daystart = df.parse(dateStr);
			dayc1.setTime(daystart);
			dayc1.add(dayc1.DATE, 1);
			daystart = dayc1.getTime();
		} catch (ParseException e) {
		} // start_date是类似"2013-02-02"的字符串
		return df.format(daystart);
	}

	/**
	 * 获取当前时间到小时
	 * @return
	 */
	public static String getDateToHour() {
		String format = "yyyy-MM-dd HH";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);
	}

	/**
	 * 获取当前时间,返回小时
	 */
	public static int getCurrentHour(){
		String format = "HH";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String H=sdf.format(dt);
		String temp=H.substring(1);
		if(!"0".equalsIgnoreCase(temp)){
			H=H.replace("0","");//如果是1-9 去掉前面的0
		}		
		return Integer.parseInt(H);
	}

	/**
	 * 获取当前月第一天
	 * @return
	 */
	public static String getCurMonthFirstDay(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();   
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String first = format.format(c.getTime());
		return first;
	}

	/**
	 * 获取当前月最后一天
	 * @return
	 */
	public static String getCurMonthLastDay() {  
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		return simpleFormate.format(calendar.getTime());  
	}

	/**
	 * 获取当前日期的下一个日期
	 * @param day
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getDateLater(int day) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 获取当前时间的前一个小时
	 * @param hour
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getDateBeforeHour(int hour) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.HOUR, hour);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
		return formatter.format(date);
	}

	/**
	 * 比较两个字符串类型的日期的大小
	 * 
	 * @param date1
	 *            "2014-02-12"
	 * @param date2
	 *            "2014-02-13"
	 * @return 1.date1>date2 0.date1=date2 -1.date1<date2
	 */
	public static int dateComparator(String date1, String date2) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		long s1 = 0L;
		long s2 = 0L;
		try {
			Date dateFirst = sd.parse(date1);
			Date dateSecond = sd.parse(date2);
			s1 = dateFirst.getTime();// 时间的毫秒
			s2 = dateSecond.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (s1 > s2) {
			return 1;
		} else if (s1 == s2) {
			return 0;
		} else {
			return -1;
		}
	}



	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || "".equals(str) || "null".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取访问域名
	 * @param request
	 * @return
	 */
	public static String getDomain(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		String domain = url.replace(uri,"");
		return domain+"/";
	}

	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	/**
	 * 判断字符串是否由数字组成
	 * @param str
	 * @return
	 */
	public static boolean judgeNumber(String str){
		boolean isNum = str.matches("[0-9]+"); 
		return isNum;
	}




	/**
	 *单位换算
	 * @return
	 */
	public static double unitConversion(String unit,int price){
		double rePrice=0L;
		if("元".equalsIgnoreCase(unit)){
			rePrice= (double)price;
		}else if("角".equalsIgnoreCase(unit)){
			rePrice=(double)price/(double)10;
		}else if("分".equalsIgnoreCase(unit)){
			rePrice=(double)price/(double)100;
		}	 
		return rePrice ;
	}

	/**
	 *获取前天的日期--yyyy-MM--dd
	 * @return
	 */
	public static String getTwoBef(){  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		//Date myDate1 = sdf.parse("2015-04-01");
		//c.setTime(myDate1);
		c.add(Calendar.DATE, -2);  
		Date day = c.getTime();
		String TwoBef = sdf.format(day);
		return TwoBef;
	}	


	/**
	 *获取昨天天的日期--yyyy-MM--dd
	 * @return
	 */
	public static String getOneBef(){  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		//Date myDate1 = sdf.parse("2015-04-01");
		//c.setTime(myDate1);
		c.add(Calendar.DATE, -1);  
		Date day = c.getTime();
		String oneBef = sdf.format(day);
		return oneBef;
	}	


	public static int getTotalDay(String year, String month) throws Exception{
		int day = 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year),Integer.parseInt(month) - 1,day);
		int last = cal.getActualMaximum(Calendar.DATE);
		return last;
	}
    /**
     * 获取指定年月的天 格式：yyyy-MM-dd
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
	public static String[] getTotalYearMonth(String year,String month)throws Exception{	
		if(month.length()==1){//页面js 有问题
			month="0"+month;
		}
		int monthLen=getTotalDay(year,month);
		String [] yearMonth=new String[monthLen];
		int day=1;
		for(int i=0;i<monthLen;i++){
			if(day<10){
				yearMonth[i]=year+"-"+month+"-"+"0"+day;
			}else{
				yearMonth[i]=year+"-"+month+"-"+day;
			}
			day++;
		}
		return yearMonth;
	}
	
	/**
     * 获取指定年月的天 格式：yyyy-MM
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
	public static String[] getYearMonth(String year)throws Exception{	
		
		String [] yearMonth=new String[12];
		int month=1;
		for(int i=0;i<12;i++){
			if(month<10){
				yearMonth[i]="'"+year+"-"+"0"+month+"'";
			}else{
				yearMonth[i]="'"+year+"-"+month+"'";
			}
			month++;
		}
		return yearMonth;
	}
	
	/**
     * 获取指定年第某季月份 格式：yyyy-S
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
	public static String[] getYearSeason(String year,int seasonOrder)throws Exception{	
		String [] yearMonth=new String[3];
		if(1==seasonOrder){
			yearMonth[0]=year+"-"+"01";
			yearMonth[1]=year+"-"+"02";
			yearMonth[2]=year+"-"+"03";
		}else if(2==seasonOrder){
			yearMonth[0]=year+"-"+"04";
			yearMonth[1]=year+"-"+"05";
			yearMonth[2]=year+"-"+"06";
		}else if(3==seasonOrder){
			yearMonth[0]=year+"-"+"07";
			yearMonth[1]=year+"-"+"08";
			yearMonth[2]=year+"-"+"09";
		}else if(4==seasonOrder){
			yearMonth[0]=year+"-"+"10";
			yearMonth[1]=year+"-"+"11";
			yearMonth[2]=year+"-"+"12";
		}
		return yearMonth;
	}
}
