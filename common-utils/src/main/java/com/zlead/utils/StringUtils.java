/*
 * @(#)StringUtils.java Copyright Bejing Passion Tech Co.,Ltd. All Rights
 * Reserved.
 */
package com.zlead.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具
 * 
 * @author Alan
 * @version 1.0
 * @see
 */
public final class StringUtils {

	/**
	 * 判断一个字符串是否为空或为"".
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}

	/**
	 * 判断一个字符串是否为空或为空格字串.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 字符串手机号验证
	 */

	public static boolean isPhone(String phone) {
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	        return false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches();
	        return isMatch;
	    }
	}
	/**
	 * 获取文件串中目录.(其实可以不包含在此工具类中的)
	 * 
	 * @param path
	 * @return
	 */
	public static String getFilePath(String path) {
		if (path != null) {
			int sepa_pos = path.lastIndexOf('/');
			if (sepa_pos < 0) {
				sepa_pos = path.lastIndexOf('\\');
			}

			if (sepa_pos > -1) {
				if (sepa_pos == 0)
					return path.substring(0, sepa_pos + 1);
				else
					return path.substring(0, sepa_pos);
			}
		}

		return path;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not
	 *         whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String,
	 * handling <code>null</code> by returning an empty String ("").
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.clean(null)          = ""
	 * StringUtils.clean("")            = ""
	 * StringUtils.clean("abc")         = "abc"
	 * StringUtils.clean("    abc    ") = "abc"
	 * StringUtils.clean("     ")       = ""
	 * </pre>
	 * 
	 * @see java.lang.String#trim()
	 * @param str
	 *            the String to clean, may be null
	 * @return the trimmed text, never <code>null</code> Method will be removed
	 *         in Commons Lang 3.0.
	 */
	public static String clean(String str) {
		return (str == null ? "" : str.trim());
	}

	/**
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be trimmed, may be null
	 * @return the trimmed string, <code>null</code> if null String input
	 */
	public static String trim(String str) {
		return (str == null ? null : str.trim());
	}

	/**
	 * <p>
	 * Splits the provided text into an array, separator specified. This is an
	 * alternative to using StringTokenizer.
	 * </p>
	 * 
	 * <p>
	 * The separator is not included in the returned String array. Adjacent
	 * separators are treated as one separator.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.split(null, *)         = null
	 * StringUtils.split("", *)           = []
	 * StringUtils.split("a.b.c", '.')    = ["a", "b", "c"]
	 * StringUtils.split("a..b.c", '.')   = ["a", "b", "c"]
	 * StringUtils.split("a:b:c", '.')    = ["a:b:c"]
	 * StringUtils.split("a\tb\nc", null) = ["a", "b", "c"]
	 * StringUtils.split("a b c", ' ')    = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param str
	 *            the String to parse, may be null
	 * @param separatorChar
	 *            the character used as the delimiter, <code>null</code> splits
	 *            on whitespace
	 * @return an array of parsed Strings, <code>null</code> if null String
	 *         input
	 * @since 2.0
	 */
	public static String[] split(String str, char separatorChar) {
		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		List<String> list = new ArrayList<String>();
		int i = 0, start = 0;
		boolean match = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match) {
					list.add(str.substring(start, i));
					match = false;
				}
				start = ++i;
				continue;
			}
			match = true;
			i++;
		}
		if (match) {
			list.add(str.substring(start, i));
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * <p>
	 * Replaces a String with another String inside a larger String, for the
	 * first <code>max</code> values of the search String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> reference passed to this method is a no-op.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replace(null, *, *, *)         = null
	 * StringUtils.replace("", *, *, *)           = ""
	 * StringUtils.replace("abaa", null, null, 1) = "abaa"
	 * StringUtils.replace("abaa", null, null, 1) = "abaa"
	 * StringUtils.replace("abaa", "a", null, 1)  = "abaa"
	 * StringUtils.replace("abaa", "a", "", 1)    = "abaa"
	 * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text
	 *            text to search and replace in, may be null
	 * @param repl
	 *            the String to search for, may be null
	 * @param with
	 *            the String to replace with, may be null
	 * @param max
	 *            maximum number of values to replace, or <code>-1</code> if no
	 *            maximum
	 * @return the text with any replacements processed, <code>null</code> if
	 *         null String input
	 */
	public static String replace(String text, String repl, String with, int max) {
		if (text == null || repl == null || with == null || repl.length() == 0 || max == 0) {
			return text;
		}

		StringBuffer buf = new StringBuffer(text.length());
		int start = 0, end = 0;
		while ((end = text.indexOf(repl, start)) != -1) {
			buf.append(text.substring(start, end)).append(with);
			start = end + repl.length();

			if (--max == 0) {
				break;
			}
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 覆盖所有.
	 * 
	 * @param source
	 * @param old
	 * @param with
	 * @return
	 * @see StringUtils#replace
	 */
	public static String replace(String source, String old, String with) {
		return replace(source, old, with, -1);
	}

	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start <code>n</code> characters
	 * from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>null</code>. An empty ("")
	 * String will return "".
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.substring(null, *)   = null
	 * StringUtils.substring("", *)     = ""
	 * StringUtils.substring("abc", 0)  = "abc"
	 * StringUtils.substring("abc", 2)  = "c"
	 * StringUtils.substring("abc", 4)  = ""
	 * StringUtils.substring("abc", -2) = "bc"
	 * StringUtils.substring("abc", -4) = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the substring from, may be null
	 * @param start
	 *            the position to start from, negative means count back from the
	 *            end of the String by this many characters
	 * @return substring from start position, <code>null</code> if null String
	 *         input
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}
		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return "";
		}

		return str.substring(start);
	}

	/**
	 * 此方法是用来计算双字节编码的实际字节数，因为在数据库中， 长度是字节数.
	 */
	public static int strByteLen(String str) {
		if (str == null)
			return 0;

		byte len[] = new byte[0];

		try {
			len = str.getBytes("GBK");
		} catch (java.io.UnsupportedEncodingException un_ex) {
			return (str.length() * 2);
		}

		int rc = len.length;
		len = null;

		return rc;
	}

	/**
	 * 以字节数截取字符串,因为数据库中字符类型长度是字节数长度.
	 */
	public static String truncateString(String str, int len) {
		if (str == null || str.length() < 1)
			return str;
		if (len < 1)
			return "";

		String rc = "";

		try {
			byte bStr[] = str.getBytes("GBK");
			if (bStr.length <= len)
				return str;

			if (isLegalChars(bStr, len))
				rc = new String(bStr, 0, len, "GBK");
			else
				rc = new String(bStr, 0, len - 1, "GBK");

			bStr = null;
		} catch (java.io.UnsupportedEncodingException un_ex) {
			return (str.substring(0, len / 2));
		}

		return rc;
	}

	/**
	 * 判断是否为ascii字符.
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isAscii(byte ch) {
		return (ch > 0);
	}

	/**
	 * 配断是否为ascii字串.
	 * 
	 * @param b
	 * @param len
	 * @return
	 */
	public static boolean isLegalChars(byte[] b, int len) {
		if (b == null || b.length < 1)
			return true;
		if (b.length <= len)
			return true;

		int count = 0;
		for (int i = 0; i < len; i++) {
			if (!isAscii(b[i]))
				count++;
		}

		return (count % 2 == 0);
	}

	/**
	 * 简单的html特殊字符替换.
	 * 
	 * @param html
	 * @return
	 */
	public static String htmlFilter(String html) {
		if (html == null || html.length() == 0)
			return html;

		String entitystr = " \n";
		StringTokenizer tmpst = new StringTokenizer(html, entitystr, true);
		StringBuffer tmpsb = new StringBuffer(html.length() + 200);
		String tmps = null;

		while (tmpst.hasMoreTokens()) {
			tmps = tmpst.nextToken();
			if (tmps.length() == 1 && entitystr.indexOf(tmps) >= 0) {
				switch (tmps.charAt(0)) {
				case ' ':
					tmpsb.append(" &nbsp;");
					break;
				case '\n':
					tmpsb.append("<br/>");
					break;
				case '<':
					tmpsb.append("&lt;");
					break;
				case '>':
					tmpsb.append("&gt;");
					break;
				}
			} else {
				tmpsb.append(tmps);
			}
		}
		return tmpsb.toString();

	}

	public static int getChineseLength(String name, String charset) throws Exception {
		int len = 0; // 定义返回的字符串长度
		int j = 0;
		// 按照指定编码得到byte[]
		byte[] b_name = name.getBytes(charset);
		while (true) {
			short tmpst = (short) (b_name[j] & 0xF0);
			if (tmpst >= 0xB0) {
				if (tmpst < 0xC0) {
					j += 2;
					len += 2;
				} else if ((tmpst == 0xC0) || (tmpst == 0xD0)) {
					j += 2;
					len += 2;
				} else if (tmpst == 0xE0) {
					j += 3;
					len += 2;
				} else if (tmpst == 0xF0) {
					short tmpst0 = (short) ((b_name[j]) & 0x0F);
					if (tmpst0 == 0) {
						j += 4;
						len += 2;
					} else if ((tmpst0 > 0) && (tmpst0 < 12)) {
						j += 5;
						len += 2;
					} else if (tmpst0 > 11) {
						j += 6;
						len += 2;
					}
				}
			} else {
				j += 1;
				len += 1;
			}
			if (j > b_name.length - 1) {
				break;
			}
		}
		return len;
	}

	public static boolean isLetter(char ch) {
		return ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'));
	}

	/**
	 * String 数组转换成int 数组
	 * @param arrs
	 * @return
	 */
	public static int[] StringToInt(String[] arrs){
		int[] ints = new int[arrs.length];
		for(int i=0;i<arrs.length;i++){
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}

	public static void main(String argv[]) throws Exception { //
          String i="0";
          int ii=Integer.parseInt(i);
          System.out.println(ii);
	}

}
