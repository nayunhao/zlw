/*
 * @(#)TripleDES.java Copyright Bejing PingXin Tech Co.,Ltd. All Rights
 * Reserved.
 */
package com.zlead.security;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import java.io.*;

/**
 * 3DES加解密(ECB模式)工具. 注意ECB模式不支持向量参数
 * 
 * @author Alan Yuan
 * @version 1.0
 * @see
 */
public final class TripleDES {
    
    static Cipher des3 = null;
    
    static {
        try {
            des3 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        } catch (Exception ex) {
        }
    }
    
    /**
     * default constructor.
     */
    private TripleDES() {
    }
    
    /**
     * 3DES加密动作.
     * 
     * @param input
     *            明文
     * @param key
     *            解密密码
     * @return byte[] 密文
     * @throws Exception
     */
    public static byte[] encrypt(byte[] input, byte[] key) throws Exception {
        SecretKey dkey = new DESSecretKey(key);
        des3.init(Cipher.ENCRYPT_MODE, dkey);
        byte[] result = des3.doFinal(input);
        return result;
    }
    
    /**
     * 3DES加密动作.输出流为加密流.
     * 
     * @param input
     * @param out
     * @param key
     * @throws Exception
     */
    public static void encrypt(byte[] input, OutputStream out, byte[] key) throws Exception {
        byte result[] = encrypt(input, key);
        out.write(result);
        out.flush();
    }
    
    /**
     * 对一个明文输入流转换为加密输出流.
     * 
     * @param in
     *            明文流.
     * @param out
     *            加密输出流.
     * @param key
     *            密钥
     * @throws Exception
     */
    public static void encrypt(InputStream in, OutputStream out, byte[] key) throws Exception {
        int len = 0;
        byte b[] = new byte[8192];
        ByteArrayOutputStream bout = new ByteArrayOutputStream(8192);
        while ((len = in.read(b)) > 0) {
            bout.write(b, 0, len);
        }
        bout.flush();
        encrypt(bout.toByteArray(), out, key);
        b = null;
    }
    
    /**
     * 3DES解密动作.
     * 
     * @param input
     *            密文
     * @param key
     *            加密密码
     * @return byte[] 明文
     * @throws Exception
     */
    public static byte[] decrypt(byte[] input, byte[] key) throws Exception {
        SecretKey dkey = new DESSecretKey(key);
        des3.init(Cipher.DECRYPT_MODE, dkey);
        // des3.
        byte[] result = des3.doFinal(input);
        
        // return removePadding(result) ;
        
        return result;
    }
    
    /**
     * 3DES解密操作.此方法针对加密流进行解密.
     * 
     * @param in
     *            加密流
     * @param key
     *            密钥
     * @return 解密流
     * @throws Exception
     */
    public static InputStream decrypt(InputStream in, byte[] key) throws Exception {
        SecretKey dkey = new DESSecretKey(key);
        des3.init(Cipher.DECRYPT_MODE, dkey);
        CipherInputStream cin = new CipherInputStream(in, des3);
        return cin;
    }
    
    // 实现定义密钥接口
    static class DESSecretKey implements SecretKey {
        /**
         * 
         */
        private static final long serialVersionUID = 7641056337088802332L;
        private byte[]            dkey             = null;
                                                   
        public DESSecretKey(byte[] b) {
            dkey = b;
        }
        
        @Override
        public byte[] getEncoded() {
            return dkey;
        }
        
        @Override
        public String getAlgorithm() {
            return "DESede";
        }
        
        @Override
        public String getFormat() {
            return "RAW";
        }
    }
}
