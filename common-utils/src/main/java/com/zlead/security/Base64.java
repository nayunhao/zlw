package com.zlead.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Base64算法
 *
 * @author yangting
 * @version 2015-04-08
 */
public class Base64
{
	/**
	 * @Description: 根据图片地址转换为base64编码字符串
	 * @Author: 
	 * @CreateTime: 
	 * @return
	 */
	public static String encodeFile(String file) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(file);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    return encode(data);
	}
	
	/**
	 * @throws  
	 * 图片的base64码转换输出图片文件
	 * @param code
	 * @param path
	 * @return
	 * @throws  
	 */
	public static boolean decodeFile(String code, String path) {
		try {
			if(code == null || code.isEmpty())
				return false;
			byte[] dt = org.apache.commons.codec.binary.Base64.decodeBase64(code.getBytes());
			//byte[] dt = decode(code);
			for (int i = 0; i < dt.length; ++i) {
				if (dt[i] < 0) {// 调整异常数据
					dt[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(dt);
			out.flush();
			out.close();			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
    /**
     * Base64编码加密
     *
     * @param data 待加密字节数组
     * @return 加密后字符串
     */
    public static String encode(byte[] data)
    {
        if (data == null) return null;
        return new String(org.bouncycastle.util.encoders.Base64.encode(data));
    }

    /**
     * Base64编码解密
     *
     * @param data 待解密字符串
     * @return 解密后字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(String data) throws CodecException
    {
        if (data == null) return null;
        try
        {
            return org.bouncycastle.util.encoders.Base64.decode(data.getBytes());
        } catch (RuntimeException e)
        {
            throw new CodecException(e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws CodecException
    {
        System.out.println(Base64.encode("a和法国和a".getBytes()));
        System.out.println(new String(Base64.decode("YeWSjOazleWbveWSjGE=")));
    }
}