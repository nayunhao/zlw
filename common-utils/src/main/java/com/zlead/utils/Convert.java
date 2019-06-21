package com.zlead.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.CRC32;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;




import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public final class Convert {

	private Convert() {
		throw new UnsupportedOperationException("不能被实例化！");
	}

	// 上传文件路径
	private static final String UPLOAD_PATH = "_res" + File.separator
			+ "upload/";

	// 为创建动态目录名而弄的
	private static final DateFormat DF_YM_4_DIR = new SimpleDateFormat(
			"yyyy/MM/");

	// 为创建文件动态目录名而弄的
	private static final DateFormat DF_YM_4_DIR_IMG = new SimpleDateFormat(
			"yyyyMM");

	// 为创建动态文件名而弄的
	private static final DateFormat DF_YMD_4_FILE = new SimpleDateFormat(
			"yyyy_MM_dd");

	// 为获取详细时间而弄的
	private static final DateFormat DF_YMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final DateFormat DF_YMDHMS2 = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	// 为获取一般日期而弄的
	private static final DateFormat DF_YMD = new SimpleDateFormat("yyyy-MM-dd");

	private final static int K = 1024;
	private final static int M = K * 1024;
	public static String getSize(long size) {
	    if (size > 10 * M) {
	        return (size / M) + "M";
	    }
	    if (size > 10 * K) {
	        return (size / K) + "K";
	    }
	    return size + "b";
	}
	
	/**
	 * 根据会计时间获取日周月的起始时间
	 * 
	 * @param h
	 *            会计时间
	 * @param d
	 *            0.日 1.周 2.月
	 * @return
	 */
	public static final long getTime(int h, int d) {
		Calendar calendar = Calendar.getInstance();

		int nowHour = calendar.get(Calendar.HOUR_OF_DAY);

		calendar.set(Calendar.HOUR_OF_DAY, h);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (d == 0) {
			if (nowHour < h) {
				calendar.add(Calendar.DATE, -1);
			}
		} else if (d == 1) {
			int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
			if (weekDay == Calendar.SUNDAY) {
				calendar.add(Calendar.WEEK_OF_YEAR, -1);
			} else {
				if (weekDay == Calendar.MONDAY) {
					if (nowHour < h) {
						calendar.add(Calendar.WEEK_OF_YEAR, -1);
					}
				}
			}

			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		} else if (d == 2) {
			int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
			if (monthDay == 1) {
				if (nowHour < h) {
					calendar.add(Calendar.DATE, -1);
					calendar.set(Calendar.DAY_OF_MONTH, 1);
				} else {
					calendar.set(Calendar.DAY_OF_MONTH, 1);
				}
			} else {
				calendar.set(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return calendar.getTimeInMillis();
	}

	/**
	 * 根据日期类型的字符串返回会计时间
	 * 
	 * @param startDate
	 *            开始的日期，格式为（yyyy-MM-dd HH）例如（2014-12-12 12）
	 * @param endDate
	 *            结束的日期，格式为（yyyy-MM-dd HH）例如（2014-12-12 12）
	 * @param HH
	 *            为餐厅的会计时间
	 * @return
	 */
	public static long[] dateStartEndMillis(String startDate, String endDate)
			throws ParseException {
		long[] times = new long[2];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		long startTimes = sdf.parse(startDate).getTime();// 毫秒
		long endTimes = sdf.parse(endDate).getTime();// 毫秒
		times[0] = startTimes;
		times[1] = endTimes;
		return times;
	}

	/**
	 * 给定Date,返回String类型全日期时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateFull(Date date) {
		if (null == date)
			return "";
		return DF_YMDHMS.format(date);
	}
	
	public static Date parseDate(String source) {
		if (null == source)
			return null;
		Date d = null;
		try {
			d = DF_YMDHMS.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 给定任意long型时间(long可以是毫秒数,也可是秒数),返回String类型日期时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateFull(long date) {
		if (date < 0)
			return "";
		String cur = System.currentTimeMillis() + "";
		Date dt = null;
		if (cur.length() - 3 == (date + "").length())
			dt = new Date(date * 1000L);
		else
			dt = new Date(date);
		return toDateFull(dt);
	}

	/**
	 * 日期类型转换成YYYY-dd-HH的String类型时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateYmd(Date date) {
		if (null == date)
			return "";
		return DF_YMD.format(date);
	}

	/**
	 * long型毫秒数转换成Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateYmd(long date) {
		if (date < 0)
			return "";

		String cur = System.currentTimeMillis() + "";
		Date dt = null;
		if (cur.length() - 3 == (date + "").length())
			dt = new Date(date * 1000L);
		else
			dt = new Date(date);

		return toDateYmd(dt);
	}

	/**
	 * 估计用来写上传动态文件夹名的
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateDir(Date date) {
		if (null == date)
			return "";
		return DF_YM_4_DIR.format(date);
	}

	public static String toDateImgDir(Date date) {
		if (null == date)
			return "";
		return DF_YM_4_DIR_IMG.format(date);
	}

	/**
	 * 估计用来写上传动态文件名的
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateFile(Date date) {
		if (null == date)
			return "";
		return DF_YMD_4_FILE.format(date);
	}

	/**
	 * String类型日期转换为Long型毫秒数
	 * 
	 * @param str
	 * @return
	 */
	public static final long getTimeLong(String str) {
		long milis = 0L;
		try {
			Date date = DF_YMD.parse(str);
			milis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return milis;
	}

	/**
	 * String类型日期转换为Long型毫秒数
	 * 
	 * @param str
	 *            (2014-10-14 14:54:46)
	 * @return
	 */
	public static final long getTimeLong2(String str) {
		long milis = 0L;
		try {
			Date date = DF_YMDHMS.parse(str);
			milis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return milis;
	}

	/**
	 * String类型日期转换为Long型毫秒数
	 * 
	 * @param str
	 *            (20141230104937)
	 * @return
	 */
	public static final long getTimeLong3(String str) {
		long milis = 0L;
		try {
			Date date = DF_YMDHMS2.parse(str);
			milis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return milis;
	}

	public static final String getWebAppBase() throws IllegalAccessException {
		// 这个是tomcat的bin目录
		String dir = System.getProperty("user.dir");
		if (null == dir)
			throw new NullPointerException("错误的路径！");

		File binDir = new File(dir);
		if (!binDir.exists())
			throw new IllegalAccessException("错误的路径！");

		return binDir.getParent() + File.separator + "webapps" + File.separator;
	}

	public static final String getClassPath(String str)
			throws IllegalAccessException {
		String webAppBase = getWebAppBase();

		return webAppBase + str + File.separator + "WEB-INF" + File.separator
				+ "classes" + File.separator;
	}

	public static final String getResBase() throws IllegalAccessException {
		// 这个是tomcat的bin目录
		String dir = System.getProperty("user.dir");
		if (null == dir)
			throw new NullPointerException("错误的路径！");

		File binDir = new File(dir);
		if (!binDir.exists())
			throw new IllegalAccessException("错误的路径！");

		return binDir.getParent() + File.separator + "webapps" + File.separator
				+ "_res" + File.separator;
	}

	public static final String getPublishBase() throws IllegalAccessException {
		return getResBase() + "publish" + File.separator;
	}

	public static final File getUploadPathBase(ServletContext application) {
		if (null == application)
			return null;

		String uploadPath = new File(application.getRealPath("/")).getParent()
				+ File.separator + UPLOAD_PATH;

		File dir = new File(uploadPath);
		if (!dir.exists())
			dir.mkdirs();
		return dir;
	}

	public static final File getUploadImgPathBase(ServletContext application) {
		if (null == application)
			return null;

		String uploadPath = new File(application.getRealPath("/")).getParent()
				+ File.separator + UPLOAD_PATH + File.separator + "employeeImg";

		File dir = new File(uploadPath);
		if (!dir.exists())
			dir.mkdirs();
		return dir;
	}

	public static final File getUploadPathCurrent(ServletContext application) {
		if (null == application)
			return null;

		String datePath = toDateDir(new Date());

		File baseFile = getUploadPathBase(application);
		if (null == baseFile || !baseFile.exists())
			return null;

		File dir = new File(baseFile, datePath);
		if (!dir.exists())
			dir.mkdirs();

		return dir;
	}

	/**
	 * 统一的上传文件方法，返回的字符串就是要存到数据库中的内容
	 * 
	 * @param src
	 *            数据源，上传过来的文件
	 * @param destName
	 *            目的文件的名字，只是名字，没有路径
	 * @param application
	 *            当前的ServletContext
	 * @return 要存到数据库中的路径，是从日期开始的
	 */
	public static String upload(File src, String destName,
			ServletContext application) {
		if (null == src || !src.exists())
			return null;
		InputStream is = null;
		OutputStream os = null;
		String datePath = null;
		try {
			is = new FileInputStream(src);
			File baseDir = getUploadPathBase(application);
			if (null == baseDir)
				return null;

			datePath = toDateDir(new Date());
			File dir = new File(baseDir, datePath);
			if (!dir.exists())
				dir.mkdirs();
			// 直接存中文会有乱码
			destName = encodeHexUrl(destName);

			File dest = new File(dir, destName);
			os = new FileOutputStream(dest);
			copy(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is);
			close(os);
		}
		return datePath + destName;
	}
	
	public static String getContentType(File file){
		if(null == file)
			return "";
		try {
			return getContentType(file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String getContentType(String fn){
		if(null == fn || "".equals(fn = fn.trim()))
			return "";
		
		Path path = Paths.get(fn);
		try {
			String res = Files.probeContentType(path);
			if(null == res){
				return ContentTypeUtils.getContentType(fn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();

		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		/*
		 * String[] temp = { "#4f8da2", "#ee6c6c", "#685046", "#619360",
		 * "#fec200" }; int index = (int) (Math.random() * temp.length); String
		 * color = temp[index];
		 */
		return "#" + r + g + b;
	}

	/**
	 * 根据上传到服务器的文件的相对路径得到真正的文件
	 * 
	 * @param fileName
	 *            存储在数据库中的路径，例如 2014/04/22/aaa_1565412514_c.png
	 * @return webapp下的_res/upload下的文件
	 */
	public static final File getUploadFile(String fileName) {
		if (null == fileName || "".equals(fileName = fileName.trim()))
			return null;
		File file = null;
		try {
			file = new File(getWebAppBase() + UPLOAD_PATH + fileName);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return file;
	}

	public static final void copy(InputStream src, OutputStream dest) {
		if (null == src || null == dest)
			return;

		try {
			if (src.available() < 1)
				return;
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = src.read(b)) > 0)
				dest.write(b, 0, len);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final byte[] hexStr2ByteArray(String hexstr) {
		if (hexstr == null)
			return null;

		if (hexstr.equals(""))
			return new byte[0];

		int s = hexstr.length() / 2;
		byte[] ret = new byte[s];
		for (int i = 0; i < s; i++) {
			ret[i] = (byte) Short.parseShort(
					hexstr.substring(i * 2, i * 2 + 2), 16);
		}
		return ret;
	}

	public static final String byteArray2HexStr(byte[] bs, int offset, int len) {
		if (bs == null)
			return null;

		if (bs.length == 0 || len <= 0)
			return "";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int tmpi = 255;
			tmpi = tmpi & bs[i + offset];
			String s = Integer.toHexString(tmpi);
			if (s.length() == 1)
				s = "0" + s;
			sb.append(s);
		}
		return sb.toString().toUpperCase();
	}

	public static final String byteArray2HexStr(byte[] bs) {
		return byteArray2HexStr(bs, 0, bs.length);
	}

	public static String encodeHexUrl(String pv) {
		if (pv == null)
			return null;

		if (pv.equals(""))
			return "";

		try {
			byte[] buf = pv.getBytes("UTF-8");
			return "=h=" + byteArray2HexStr(buf);
		} catch (UnsupportedEncodingException ee) {
			throw new RuntimeException(ee.getMessage());
		}
	}

	public static final String decodeHexUrl(String hexu) {
		if (hexu == null)
			return null;

		if (hexu.startsWith("=h=")) {
			hexu = hexu.substring(3);
			byte[] buf = hexStr2ByteArray(hexu);
			if (buf == null)
				return null;
			try {
				return new String(buf, "UTF-8");
			} catch (UnsupportedEncodingException ee) {
				throw new RuntimeException(ee.getMessage());
			}
		} else if (hexu.startsWith("=u=")) {
			hexu = hexu.substring(3);

			try {
				String s = URLDecoder.decode(hexu, "UTF-8");
				if (s.indexOf('%') < 0)
					return s;

				return URLDecoder.decode(URLDecoder.decode(hexu, "US-ASCII"),
						"UTF-8");
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} else {
			return hexu;
		}
	}

	public static final String decodeSmartUrl(String u) {
		if (u == null)
			return null;
		if ("".equals(u))
			return u;

		if (u.startsWith("=h=")) {
			return decodeHexUrl(u);
		} else if (u.startsWith("=u=")) {// 客户端脚本提供的utf8urlencode做的编码--并且该编码没有通过ie标准提交
											// 该url通过ajax方式直接提交的
			u = u.substring(3);

			String s = null;
			try {
				s = URLDecoder.decode(u, "UTF-8");
				if (s.indexOf('%') < 0)
					return s;

				return URLDecoder.decode(URLDecoder.decode(u, "US-ASCII"),
						"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
				return s;
			}
		} else {
			return u;
		}
	}

	public static void close(Closeable c) {
		if (null == c)
			return;
		try {
			c.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		c = null;
	}

	public static String readStreamLine(InputStream inputs, int maxlen)
			throws Exception {
		StringBuilder firstline = new StringBuilder();
		int c;
		while ((c = inputs.read()) >= 0) {
			if (c == '\n') {
				return firstline.toString();
			}

			firstline.append((char) c);
			if (firstline.length() > maxlen) {
				throw new Exception("invalid line,big than max len!");
			}
		}
		throw new Exception("invalid line,big than max len!");
	}

	public static JSONObject obj2json(Object obj) {
		return obj2jsonExceptsFields(obj, null);
	}

	public static JSONObject obj2jsonExceptsFields(Object obj,
			List<String> excepts) {
		Map<String, Object> map = xormObj2map(obj, excepts, false);
		JSONObject json = new JSONObject(map);
		return json;
	}

	public static JSONObject obj2jsonContainFields(Object obj,
			List<String> excepts) {
		Map<String, Object> map = xormObj2map(obj, excepts, true);
		JSONObject json = new JSONObject(map);
		return json;
	}

	public static Map<String, Object> xormObj2map(Object obj) {
		return xormObj2map(obj, null, false);
	}

	public static Map<String, Object> xormObj2map(Object obj,
			List<String> fieldList, boolean contains) {
		if (null == obj)
			return null;

		if (contains) {
			if (null == fieldList || fieldList.isEmpty())
				return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();

		if (null == fields || 0 == fields.length)
			return null;

		for (Field field : fields) {
			field.setAccessible(true);
			String fname = field.getName();
			if (null != fieldList && fieldList.size() > 0) {
				if (!contains) {
					if (fieldList.contains(fname))
						continue;
				} else {
					if (!fieldList.contains(fname))
						continue;
				}
			}

			try {
				Object val = field.get(obj);
				map.put(fname, val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public static int getCrc(byte[] b) {
		CRC32 crc32 = new CRC32();
		crc32.update(b);
		return (int) crc32.getValue();
	}

	/**
	 * 把中文的字符串转换为汉语拼音 第一个字母的缩写
	 * 
	 * @param chinese_str
	 * @return
	 */
	public static String cn2py(String chinese_str) {
		if (chinese_str == null)
			return null;

		if ("".equals(chinese_str))
			return chinese_str;

		int len = chinese_str.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String[] phs = PinyinHelper.toHanyuPinyinStringArray(chinese_str
					.charAt(i));
			if (phs == null || phs.length <= 0)
				continue;

			if (phs[0].length() <= 0)
				continue;

			sb.append(phs[0].charAt(0));
		}

		return sb.toString();
	}

	public static String plainToJsStr(String txt) {
		if (txt == null)
			return null;

		if (txt.equals(""))
			return "";

		StringBuilder sb = new StringBuilder();
		int len = txt.length();
		for (int i = 0; i < len; i++) {
			char c = txt.charAt(i);
			switch (c) {
			case '\'':
				sb.append("\\\'");
				break;
			case '\"':
				sb.append("\\\"");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\n':
				sb.append("\\n");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

	public static boolean isMobileReq(HttpServletRequest request) {
		if (null == request)
			return false;

		String userAgent = request.getHeader("user-agent");
		if (StringUtils.isBlank(userAgent))
			return false;

		userAgent = userAgent.toLowerCase();
		return userAgent.indexOf("mobile") != -1;
	}

	/**
	 * 报表相关,获取一周坐标注释(已过时)
	 * 
	 * @return
	 */
	public static String getOneWeekDay2Now() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -7);
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0, j = 6; i < j; i++) {
			now.add(Calendar.DATE, 1);
			sb.append("'" + now.get(Calendar.DAY_OF_MONTH) + "日',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(",'今日'");
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 新的获取一周注释
	 * 
	 * @param h
	 * @return
	 */
	public static String getOneWeekDay2Now(int h) {
		Calendar now = Calendar.getInstance();
		int nowHour = now.get(Calendar.HOUR_OF_DAY);
		if (nowHour < h) {
			now.add(Calendar.DATE, -8);
		} else {
			now.add(Calendar.DATE, -7);
		}
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0, j = 6; i < j; i++) {
			now.add(Calendar.DATE, 1);
			sb.append("'" + now.get(Calendar.DAY_OF_MONTH) + "日',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(",'会计日'");
		sb.append("]");
		return sb.toString();
	}

	public static String[] getOneWeek2Now() {
		Calendar now = Calendar.getInstance();
		String[] res = new String[7];
		for (int i = 0, j = res.length; i < j; i++) {
			now.add(Calendar.DATE, -1);
			res[i] = num2WeekDay(now.get(Calendar.DAY_OF_WEEK));
		}
		return res;
	}

	public static String num2WeekDay(int num) {
		switch (num) {
		case Calendar.SUNDAY:
			return "星期日";
		case Calendar.MONDAY:
			return "星期一";
		case Calendar.TUESDAY:
			return "星期二";
		case Calendar.WEDNESDAY:
			return "星期三";
		case Calendar.THURSDAY:
			return "星期四";
		case Calendar.FRIDAY:
			return "星期五";
		case Calendar.SATURDAY:
			return "星期六";
		default:
			return "星期日";
		}
	}

	/**
	 * 返回一个只有2个Element的long型数组为日期的起始和结束时间(已过时)
	 * 
	 * @param c
	 * @return
	 */
	public static long[] getDayStartEnd(Calendar c) {
		long[] res = new long[2];

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		res[0] = c.getTimeInMillis();

		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		res[1] = c.getTimeInMillis();

		return res;
	}

	/**
	 * 返回一个只有2个Element的long型数组为日期的起始和结束时间,依据会计时间
	 * 
	 * @param c
	 * @param h
	 * @return
	 */
	public static long[] getDayStartEnd(Calendar c, int h) {
		long[] res = new long[2];
		c = (Calendar) c.clone();
		// 好好考虑一下
		if (h == 0) {
			c.set(Calendar.HOUR_OF_DAY, h);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			res[0] = c.getTimeInMillis();

			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);
			res[1] = c.getTimeInMillis();
		} else {
			c.set(Calendar.HOUR_OF_DAY, h);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			res[0] = c.getTimeInMillis();

			c.set(Calendar.HOUR_OF_DAY, h - 1);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);
			c.add(Calendar.DATE, 1);
			res[1] = c.getTimeInMillis();
		}

		return res;
	}

	public static long[] getTodayStartEnd() {
		Calendar c = Calendar.getInstance();
		return getDayStartEnd(c);
	}

	/**
	 * 得到某一年的起始时间和结束时间
	 * 
	 * @param year
	 * @return
	 */
	public static long[] getYearStartEnd(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		long st = c.getTimeInMillis();

		c.set(Calendar.MONTH, 11);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);

		long et = c.getTimeInMillis();
		return new long[] { st, et };
	}

	/**
	 * 得到某一月份的起始结束时间
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static long[] getMonthStartEnd(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		long st = c.getTimeInMillis();

		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);

		long et = c.getTimeInMillis();
		return new long[] { st, et };
	}

	public static List<long[]> getWeekStartEndsInMonth(int year, int mon) {
		return getWeekStartEndsInMonth(year, mon, false);
	}

	/**
	 * 获得一个月之内的所有周的起始结束时间 第一周和最后一周如果有天不在本月之内,则排除
	 * 
	 * @param year
	 * @param mon
	 * @param b_week_start_monday
	 *            是否用星期一作为周的开始,缺省是星期天作为周开始
	 * @return
	 */
	public static List<long[]> getWeekStartEndsInMonth(int year, int mon,
			boolean b_week_start_monday) {
		List<long[]> rets = new ArrayList<long[]>();
		Calendar sc = Calendar.getInstance();
		sc.set(Calendar.YEAR, year);
		sc.set(Calendar.MONTH, mon - 1);
		sc.set(Calendar.DAY_OF_MONTH, 1);

		sc.set(Calendar.HOUR_OF_DAY, 0);
		sc.set(Calendar.MINUTE, 0);
		sc.set(Calendar.SECOND, 0);
		sc.set(Calendar.MILLISECOND, 0);
		// 第一周
		long s1 = sc.getTimeInMillis();

		Calendar ec = Calendar.getInstance();
		ec.setTimeInMillis(s1);

		int df = ec.get(Calendar.DAY_OF_WEEK);
		if (b_week_start_monday) {
			if (df != Calendar.SUNDAY)
				ec.add(Calendar.DAY_OF_MONTH, Calendar.SATURDAY + 1 - df);
		} else {
			ec.add(Calendar.DAY_OF_MONTH, Calendar.SATURDAY - df);
		}
		ec.set(Calendar.HOUR_OF_DAY, 23);
		ec.set(Calendar.MINUTE, 59);
		ec.set(Calendar.SECOND, 59);
		ec.set(Calendar.MILLISECOND, 999);
		long e1 = ec.getTimeInMillis();
		rets.add(new long[] { s1, e1 });

		sc.set(Calendar.DAY_OF_MONTH, ec.get(Calendar.DAY_OF_MONTH) - 6);

		for (int i = 0; i < 3; i++) {
			sc.add(Calendar.DAY_OF_MONTH, 7);
			ec.add(Calendar.DAY_OF_MONTH, 7);
			rets.add(new long[] { sc.getTimeInMillis(), ec.getTimeInMillis() });
		}

		for (int i = 0; i < 2; i++) {
			sc.add(Calendar.DAY_OF_MONTH, 7);
			if (sc.get(Calendar.MONTH) + 1 != mon)
				return rets;

			ec.add(Calendar.DAY_OF_MONTH, 7);
			if (ec.get(Calendar.MONTH) + 1 == mon) {
				rets.add(new long[] { sc.getTimeInMillis(),
						ec.getTimeInMillis() });
			} else {// read end of month day
				ec.add(Calendar.MONTH, -1);
				ec.set(Calendar.DAY_OF_MONTH,
						ec.getActualMaximum(Calendar.DAY_OF_MONTH));
				rets.add(new long[] { sc.getTimeInMillis(),
						ec.getTimeInMillis() });
				break;
			}
		}

		return rets;
	}

	/**
	 * 截取小数点后两位，不四舍五入
	 */
	public static float DoubleInterceptTwo(double number) {
		return new BigDecimal("" + number).setScale(2, BigDecimal.ROUND_DOWN)
				.floatValue();// 不是四舍五入
	}

	/**
	 * 截取小数点后两位，四舍五入
	 */
	public static float DoubleRoundDown(double number) {
		return new BigDecimal("" + number)
				.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();// 四舍五入
	}

	/**
	 * 截取小数点后两位，不四舍五入
	 */
	public static float FloatInterceptTwo(float number) {
		return new BigDecimal("" + number).setScale(2, BigDecimal.ROUND_DOWN)
				.floatValue();// 不是四舍五入
	}

	/**
	 * 截取小数点后两位，四舍五入
	 */
	public static float FloatRoundDown(float number) {
		return new BigDecimal("" + number)
				.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();// 四舍五入
	}

	/**
	 * 截取小数点后一位，四舍五入
	 */
	public static float FloatRoundDown1(float number) {
		return new BigDecimal("" + number)
				.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();// 四舍五入
	}

	/**
	 * 截取小数点后一位，double四舍五入
	 */
	public static double DoubleRoundDown1(double number) {
		return new BigDecimal("" + number)
				.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();// 四舍五入
	}

	/**
	 * 截取小数点后两位，double四舍五入
	 */
	public static double DoubleRoundDown2(double number) {
		return new BigDecimal("" + number)
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();// 四舍五入
	}

	/**
	 * 将float转化成int
	 */
	public static int Float2Int(float number) {
		BigDecimal a = new BigDecimal(number);
		int b = a.intValue();
		return b;// 四舍五入
	}


	/**
	 * Long类型的毫秒数转换为String类型的时间 例如(1413561600000 转换为 2014-10-18)
	 * 
	 * @param timeMillis
	 * @return
	 * @throws ParseException
	 */
	public static String getLongTimeParsetoString(long timeMillis)
			throws ParseException {
		if (timeMillis < 1)
			return "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateMillis = new Date(timeMillis);
		String dateString = format.format(dateMillis);
		Date date = format.parse(dateString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return format.format(date);

	}

	/**
	 * String类型的毫秒数转换为Long类型的时间 例如(2014-10-18 转换为 1413561600000)
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static long getStringTimeParsetoLong(String time)
			throws ParseException {

		if (null == time || time.length() < 1)
			return 0L;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long millionSeconds = sdf.parse(time).getTime();

		return millionSeconds;
	}
	/**
	 * String类型的毫秒数转换为Long类型的时间 例如(2014-10-18 转换为 1413561600000)
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static long getStringTimeParsetoLongFull(String time)
			throws ParseException {

		if (null == time || time.length() < 1)
			return 0L;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long millionSeconds = sdf.parse(time).getTime();

		return millionSeconds;
	}
	/**
	 * 表单中的String类型，在存入数据库前先进行转换，以免存入HTML代码导致以后展示到页面时出错。
	 * 例如(<script>alert(123);</script>转换为
	 * &lt;script&gt;alert(123);&lt;/script&gt;)
	 * 
	 * @param 表单中String
	 * @return
	 */
	public static String escapeHTML(String s) {
		StringBuilder buf = new StringBuilder();

		for (char c : s.toCharArray()) {
			switch (c) {
			case '&':
				buf.append("&amp;");
				break;
			case '<':
				buf.append("&lt;");
				break;
			case '>':
				buf.append("&gt;");
				break;
			default:
				buf.append(c);
			}
		}

		return buf.toString();
	}

	/**
	 * img路径
	 * imagePath 数据库存的地址
	 * defaultPath 默认地址
	 * request 
	 * 返回图片路径
	 */
	public static String getImgPath(String imagePath, String defaultPath,
			HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String imgpath = basePath1  + "/_res/upload/";
		return 	 StringUtils.isNotBlank(imagePath)?imgpath +
				imagePath:basePath+defaultPath;

	}
	
	public static String getFileMD5(File file) {
		if (!file.isFile()){
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in=null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}
}
