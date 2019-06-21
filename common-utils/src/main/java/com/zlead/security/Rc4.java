package com.zlead.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Title:        RC4算法 
 * Description:  RC4算法，对称算法
 */
public class Rc4
{
    private static final String alg = "RC4";

    /**
     * RC4加密
     *
     * @param src 待加密数据
     * @param key 加密私钥，长度必须是8的倍数
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws CodecException 异常
     */
    public static byte[] encode(byte[] src, String key) throws CodecException
    {
        try
        {
            // 从原始密匙数据创建SecretKeySpec对象
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "RC4");
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(alg);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    /**
     * RC4解密
     *
     * @param src 待解密字符串
     * @param key 解密私钥，长度必须是8的倍数
     * @return 解密后的字节数组
     * @throws CodecException 异常
     */
    public static byte[] decode(byte[] src, String key) throws CodecException
    {
        try
        {
            // 从原始密匙数据创建SecretKeySpec对象
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "RC4");
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(alg);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(src);
        } catch (Exception e)
        {
            throw new CodecException(e);
        }
    }

    public static void main(String[] args) throws CodecException
    {
        System.out.println(Base64.encode(Rc4.encode("12345".getBytes(), "11111111")));
    }
}
