package com.zlead.security;
import com.zlead.exception.SystemException;

/**
 * 统一DESede算法加密，采用Base64编码
 * 
 * @author yangting
 * 
 */
public class Digests
{

	private static final String	KEY		= "wKhebOESS0JkgjaY8ucifC2F";

	private static final byte[]	IVPS	= "9mLlxQar".getBytes();

	/**
	 * DESede加密,并采用Base64编码
	 * 
	 * @param src
	 *            待加密数据
	 * @throws CodecException
	 *             异常
	 */
	public static String encode(String src)
	{
		try
		{
			return Base64.encode(Desede.encode(src.getBytes(), KEY, IVPS));
		}
		catch (Exception e)
		{
			throw SystemException.convertSystemException(e);
		}
	}

	/**
	 * DESede解密
	 * 
	 * @param src
	 *            待解密字符串
	 * @param key
	 *            解密私钥，长度必须是8的倍数
	 * @param ivps
	 *            IvParameterSpec
	 * @return 解密后的字节数组
	 * @throws CodecException
	 *             异常
	 */
	public static String decode(String src)
	{
		try
		{
			return new String(Desede.decode(Base64.decode(src), KEY, IVPS));
		}
		catch (Exception e)
		{
			throw SystemException.convertSystemException(e);
		}
	}

}