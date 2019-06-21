/*
 * @(#)AesUtil.java Copyright BeiJing PingXin Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * AES加密工具
 * 
 * @author Alan Yuan
 *        
 */
public class AesUtil {
    private static final String SHA1PRNG      = "SHA1PRNG";
    private static final String AES           = "AES";
    private static String       strDefaultKey = "1234567890";
    private Cipher              encryptCipher = null;
    private Cipher              decryptCipher = null;
                                              
    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     *             
     * @throws Exception
     */
    public AesUtil() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        this(strDefaultKey);
    }
    
    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws Exception
     */
    public AesUtil(String strKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Key key = getKey(strKey.getBytes());
        
        encryptCipher = Cipher.getInstance(AES);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        
        decryptCipher = Cipher.getInstance(AES);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }
    
    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException {
        return encryptCipher.doFinal(arrB);
    }
    
    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public String encrypt(String strIn) throws IllegalBlockSizeException, BadPaddingException {
        return Encoder.toHexString(encrypt(strIn.getBytes()));
    }
    
    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws IllegalBlockSizeException, BadPaddingException {
        return decryptCipher.doFinal(arrB);
    }
    
    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public String decrypt(String strIn) throws IllegalBlockSizeException, BadPaddingException {
        return new String(decrypt(Encoder.hexToBytes(strIn)));
    }
    
    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * 
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws NoSuchAlgorithmException
     * @throws java.lang.Exception
     */
    private SecretKey getKey(byte[] arrBTmp) throws NoSuchAlgorithmException {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        
        // 生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG);
        secureRandom.setSeed(arrB);
        keyGenerator.init(128, secureRandom);
        SecretKey key = keyGenerator.generateKey();
        
        return key;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            String test = "Hellow Word! 你好！";
            // DESPlus des = new DESPlus();//默认密钥
            AesUtil des = new AesUtil();// 自定义密钥
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encrypt(test));
            System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test)));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
