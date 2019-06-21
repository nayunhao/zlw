package com.zlead.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @Title:        DES算法 
 * @author yangting
 * @version 2015-5-2 10:28:26
 */
public class Des
{
    private static final String alg = "DES";
    private static final String transformation = "DES/ECB/PKCS5Padding";

    /**
     * DES加密
     *
     * @param src 待加密数据
     * @param key 加密私钥，长度必须是8的倍数
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CodecException 异常
     */
    public static byte[] encode(byte[] src, String key) throws CodecException
    {
        return encode(src, key, transformation);
    }

    /**
     * DES加密
     *
     * @param src            待加密数据
     * @param key            加密私钥，长度必须是8的倍数
     * @param transformation String
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CodecException 异常
     */
    public static byte[] encode(byte[] src, String key, String transformation) throws CodecException
    {
        try
        {
            //DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密匙数据创建DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(transformation);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * DES解密
     *
     * @param src 待解密字符串
     * @param key 解密私钥，长度必须是8的倍数
     * @return 解密后的字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(byte[] src, String key) throws CodecException
    {
    	System.out.println("---------------------------"+key);
        return decode(src, key, transformation);
    }

    /**
     * DES解密
     *
     * @param src            待解密字符串
     * @param key            解密私钥，长度必须是8的倍数
     * @param transformation String
     * @return 解密后的字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(byte[] src, String key, String transformation) throws CodecException
    {
        try
        {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 从原始密匙数据创建一个DESKeySpec对象
            DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
            // 一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(transformation);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            // 现在，获取数据并解密
            // 正式执行解密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
        	e.printStackTrace();
            throw new CodecException(e);
        }
    }

    public static void main(String[] args) throws Exception
    {
    	
       System.out.println(Base64.encode(Des.encode("jdbc:sqlserver://192.168.240.246\\INST_SPADB;DatabaseName=FetionCore".getBytes(), "huodong12345")));
        	
       System.out.println(Base64.encode(Des.encode("jdbc:sqlserver://192.168.241.101\\INST_ACHVDB;DatabaseName=FetionVideo".getBytes(), "huodong12345")));
       System.out.println(Base64.encode(Des.encode("FetionCore_Worker".getBytes(), "huodong12345")));
       
       System.out.println(Base64.encode(Des.encode("FetionVideo".getBytes(), "huodong12345")));       
       System.out.println(new String(Des.decode(Base64.decode("lIBsfnzllmw9u/AGPJkf5qrRmxxU34GIDofHhD65S0EJDZAYIjAgiIdubKPkJvpD3GH+45cwHXaT/e51ee4lpX4Rw+d3ktmz"), "huodong12345")));
       //new String(Des.decode(Base64.decode(url), this.key))
       
       System.out.println(Base64.encode(Des.encode("jdbc:mysql://r_ibmp.dbop.com:3384/ibmp_q4?zeroDateTimeBehavior=convertToNull&autoReconnect=true".getBytes(), "huodong12345")));     
       System.out.println(Base64.encode(Des.encode("zz_user".getBytes(), "huodong12345"))); 
       System.out.println(Base64.encode(Des.encode("zZ@ibmp_2014".getBytes(), "huodong12345"))); 

       System.out.println(new String(Des.decode(Base64.decode("aX0jmfC9u3Fh/4U2JGTxM1KcLyJEPUFYbl4XuM0SyRU4YRvHcjnLwnP+0wQeIsnIdWtKg/bUd6FAVW0e+RIpn2JRNTXsnE7NAva32h/3JjeDL+dUi5OjIa57HVJwkuhmr/rmaz2x+B9XJ1aNfHAQgw=="), "11111111       aX0jmfC9u3Fh/4U2JGTxM1KcLyJEPUFYbl4XuM0SyRU4YRvHcjnLwnP+0wQeIsnIdWtKg/bUd6FAVW0e+RIpn2JRNTXsnE7NAva32h/3JjeDL+dUi5OjIa57HVJwkuhmr/rmaz2x+B9XJ1aNfHAQgw==")));     
       
       System.out.println(new String(Des.decode(Base64.decode("YLUxEe8MNWGijHbWqQHxPw=="), "huodong12345")));
       
    }
}
