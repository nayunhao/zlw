package com.zlead.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Title:        DESede算法 
 * Description:  主要用于兼容.net算法，要求私钥key必须为24位，IVPS值必须为8位长度
 *
 * @author yangting
 * @version 2015-4-2 10:28:26
 */
public class Desede
{
    private static final String alg = "DESede";
    private static final String transformation = "DESede/CBC/PKCS5Padding";

    /**
     * DESede加密
     *
     * @param src  待加密数据
     * @param key  加密私钥，长度必须是8的倍数
     * @param ivps IvParameterSpec
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CodecException 异常
     */
    public static byte[] encode(byte[] src, String key, byte[] ivps) throws CodecException
    {
        return encode(src, key, ivps, transformation);
    }

    /**
     * DESede加密
     *
     * @param src            待加密数据
     * @param key            加密私钥，长度必须是8的倍数
     * @param ivps           IvParameterSpec
     * @param transformation String
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CodecException 异常
     */
    public static byte[] encode(byte[] src, String key, byte[] ivps, String transformation) throws CodecException
    {
        try
        {
            // 为 CBC 模式创建一个用于初始化的 vector 对象
            IvParameterSpec IvParameters = new IvParameterSpec(ivps);
            // 从原始密匙数据创建DESKeySpec对象
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把KeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(transformation);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, IvParameters);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * DESede解密
     *
     * @param src  待解密字符串
     * @param key  解密私钥，长度必须是8的倍数
     * @param ivps IvParameterSpec
     * @return 解密后的字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(byte[] src, String key, byte[] ivps) throws CodecException
    {
        return decode(src, key, ivps, transformation);
    }

    /**
     * DESede解密
     *
     * @param src            待解密字符串
     * @param key            解密私钥，长度必须是8的倍数
     * @param ivps           IvParameterSpec
     * @param transformation String
     * @return 解密后的字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(byte[] src, String key, byte[] ivps, String transformation) throws CodecException
    {
        try
        {
            // 为 CBC 模式创建一个用于初始化的 vector 对象
            IvParameterSpec IvParameters = new IvParameterSpec(ivps);
            // 从原始密匙数据创建一个DESKeySpec对象
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
            // 创建一个密匙工厂，然后用它把KeySpec对象转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);
            SecretKey securekey = keyFactory.generateSecret(dks);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(transformation);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, IvParameters);
            // 现在，获取数据并解密
            // 正式执行解密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public static void main(String[] args) throws Exception
    {
        System.out.println(Base64.encode(Desede.encode("1qaz2ws".getBytes(), "01234567890123456789012345678912", "23456789".getBytes())));
        String decode = new String(Desede.decode(Base64.decode("uqtJiz1JMw4="), "111111112222222233333333", "12345678".getBytes()));
        System.out.println(decode);
    }
}
