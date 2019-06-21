/*
 * @(#)Encoder.java Copyright Bejing PingXin Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.security;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个十六进制编码处理器
 * 
 * @author Alan Yuan
 * @version 1.0
 * @see
 */
public final class Encoder {
    
    public static final String    ALGORITHM_MD2    = "MD2";
    public static final String    ALGORITHM_MD5    = "MD5";
    public static final String    ALGORITHM_SHA1   = "SHA-1";
    public static final String    ALGORITHM_SHA256 = "SHA-256";
    public static final String    ALGORITHM_SHA384 = "SHA-384";
    public static final String    ALGORITHM_SHA512 = "SHA-512";
                                                   
    private static Logger         logger           = LoggerFactory.getLogger(Encoder.class);
                                                   
    protected static final byte[] encodingTable    = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4',
            (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd',
            (byte) 'e', (byte) 'f' };
            
    protected static final byte[] decodingTable    = new byte[128];
                                                   
    static {
        for (int i = 0; i < encodingTable.length; i++) {
            decodingTable[encodingTable[i]] = (byte) i;
        }
        
        decodingTable['A'] = decodingTable['a'];
        decodingTable['B'] = decodingTable['b'];
        decodingTable['C'] = decodingTable['c'];
        decodingTable['D'] = decodingTable['d'];
        decodingTable['E'] = decodingTable['e'];
        decodingTable['F'] = decodingTable['f'];
    }
    
    /**
     * 
     * @param data
     * @param off
     * @param length
     * @param out
     * @return
     * @throws IOException
     */
    public static int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        for (int i = off; i < (off + length); i++) {
            int v = data[i] & 0xff;
            
            out.write(encodingTable[(v >>> 4)]);
            out.write(encodingTable[v & 0xf]);
        }
        
        return length * 2;
    }
    
    /**
     * 对二进制编码成16进制字串.
     * 
     * @param b
     * @return
     */
    public static String toHexString(byte[] b) {
        if (b == null || b.length == 0)
            return "";
        try {
            ByteArrayOutputStream aout = new ByteArrayOutputStream(512);
            encode(b, 0, b.length, aout);
            
            String rtn = new String(aout.toByteArray()).toUpperCase();
            
            aout.close();
            return rtn;
        } catch (Exception ex) {
            return "";
        }
    }
    
    /**
     * 
     * @param data
     * @param out
     * @return
     * @throws IOException
     */
    public static int decode(String data, OutputStream out) throws IOException {
        byte b1, b2;
        int length = 0;
        int end = data.length();
        
        while (end > 0) {
            if (!ignore(data.charAt(end - 1))) {
                break;
            }
            
            end--;
        }
        
        int i = 0;
        while (i < end) {
            while (i < end && ignore(data.charAt(i))) {
                i++;
            }
            b1 = decodingTable[data.charAt(i++)];
            while (i < end && ignore(data.charAt(i))) {
                i++;
            }
            b2 = decodingTable[data.charAt(i++)];
            
            out.write((b1 << 4) | b2);
            length++;
        }
        
        return length;
    }
    
    /**
     * 对16进制字串转成二进制数组.
     * 
     * @param hexStr
     * @return
     */
    public static byte[] hexToBytes(String hexStr) {
        if (hexStr == null || hexStr.length() == 0)
            return new byte[0];
        try {
            ByteArrayOutputStream aout = new ByteArrayOutputStream(512);
            decode(hexStr, aout);
            aout.flush();
            return aout.toByteArray();
        } catch (Exception ex) {
            return new byte[0];
        }
    }
    
    private static boolean ignore(char c) {
        return (c == '\n' || c == '\r' || c == '\t' || c == ' ');
    }
    
    /**
     * 加密出于MessageDigest，默认MD5
     * 
     * @param input
     *            需要加密的字符
     * @param algorithm
     *            加密(摘要)算法名称(MD2,MD5,SHA1,SHA256,SHA384,SHA512)
     * @return
     */
    public static byte[] algorithmEncode(byte[] input, String algorithm) {
        if (input == null)
            return null;
            
        MessageDigest md = null;
        byte[] output = null;
        
        try {
            if (algorithm == null || algorithm.equals("")) {
                algorithm = ALGORITHM_MD5;
            }
            md = MessageDigest.getInstance(algorithm);
            md.update(input);
            output = md.digest(); // to HexString
        } catch (NoSuchAlgorithmException e) {
            logger.error("Invalid algorithm." + e);
            return null;
        }
        return output;
    }
    
    /**
     * 加密出于MessageDigest，默认MD5
     * 
     * @param source
     *            需要加密的字符串
     * @param algorithm
     *            加密(摘要)算法名称(MD2,MD5,SHA1,SHA256,SHA384,SHA512)
     * @return
     */
    public static String algorithmEncode(String source, String algorithm) {
        if (source == null)
            return null;
            
        byte[] bt;
        try {
            bt = source.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            logger.error("Unsupported Encoding." + e);
            return null;
        }
        return toHexString(algorithmEncode(bt, algorithm)).toLowerCase();
    }
    
    /**
     * 做MD5杂凑.
     * 
     * @param text
     *            String
     * @return
     */
    public static String md5Encode(String text) {
        return algorithmEncode(text, ALGORITHM_MD5);
    }
    
    /**
     * 做MD5杂凑.
     * 
     * @param input
     *            byte[]
     * @return
     */
    public static byte[] md5Encode(byte[] input) {
        return algorithmEncode(input, ALGORITHM_MD5);
    }
}
