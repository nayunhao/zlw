/*
 * @(#)XBase64.java Copyright Bejing PingXin Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.security;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 变种Base64编码算法实现.由于Base64方式会出现'+','=','/'.
 * 而对于URLEncoder会对这些字符进行转换,所以此算法只是替换这几个字符. 本质上还是Base64算法.
 * 
 * @author Alan Yuan
 * @version 1.0
 */
public class XBase64 {
    private final static byte[] encodingTable = { (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e',
            (byte) 'f', (byte) 'g', (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n',
            (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's', (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w',
            (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
            (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E',
            (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I', (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N',
            (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U', (byte) 'V', (byte) 'W',
            (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) '_', (byte) '.' };
            
    private static byte         padding       = (byte) '-';
    private static int          BUF_SIZE      = 1024;
    /*
     * set up the decoding table.
     */
    private final static byte[] decodingTable = new byte[128];
                                              
    static {
        for (int i = 0; i < encodingTable.length; i++) {
            decodingTable[encodingTable[i]] = (byte) i;
        }
    }
    
    /**
     * default constructor.
     */
    private XBase64() {
    }
    
    /**
     * 编码操作.
     * 
     * @param data
     *            原始字符串字节流
     * @return 编码后的字符串流
     */
    public static byte[] encode(byte[] data) {
        // System.out.println("XBase64 encode begin");
        if (data == null)
            return null;
        // System.out.println("XBase64 encode end");
        return encode(data, 0, data.length);
    }
    
    /**
     * 
     * @param data
     * @param offset
     * @param length
     * @return
     */
    public static byte[] encode(byte[] data, int offset, int length) {
        if (data == null)
            return null;
        if (data.length == 0 || length == 0)
            return new byte[0];
        if ((data.length - offset) < length)
            throw new ArrayIndexOutOfBoundsException("data.length<offset+length");
            
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUF_SIZE);
        // int length = data.length() ;
        // int modulus = data.length % 3;
        int modulus = length % 3;
        // int dataLength = ( data.length - modulus);
        int dataLength = (length - modulus);
        int a1, a2, a3;
        
        for (int i = offset; i < dataLength; i += 3) {
            a1 = data[i] & 0xff;
            a2 = data[i + 1] & 0xff;
            a3 = data[i + 2] & 0xff;
            
            out.write(encodingTable[(a1 >>> 2) & 0x3f]);
            out.write(encodingTable[((a1 << 4) | (a2 >>> 4)) & 0x3f]);
            out.write(encodingTable[((a2 << 2) | (a3 >>> 6)) & 0x3f]);
            out.write(encodingTable[a3 & 0x3f]);
        }
        
        /*
         * process the tail end.
         */
        int b1, b2, b3;
        int d1, d2;
        
        switch (modulus) {
            case 0: /* nothing left to do */
                break;
            case 1:
                d1 = data[dataLength] & 0xff;
                b1 = (d1 >>> 2) & 0x3f;
                b2 = (d1 << 4) & 0x3f;
                
                out.write(encodingTable[b1]);
                out.write(encodingTable[b2]);
                out.write(padding);
                out.write(padding);
                break;
            case 2:
                d1 = data[dataLength] & 0xff;
                d2 = data[dataLength + 1] & 0xff;
                
                b1 = (d1 >>> 2) & 0x3f;
                b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
                b3 = (d2 << 2) & 0x3f;
                
                out.write(encodingTable[b1]);
                out.write(encodingTable[b2]);
                out.write(encodingTable[b3]);
                out.write(padding);
                break;
        }
        // out.flush();
        byte result[] = out.toByteArray();
        return result;
    }
    
    /**
     * 不带字符编码方式的XBase64解码操作.
     * 
     * @param input
     *            编码前的字符串
     * @return 编码后的字符串
     */
    public static String encode(String input) {
        if (input == null)
            return null;
        return new String(encode(input.getBytes()));
    }
    
    /**
     * 带字符编码方式的Base64编码操作.
     * 
     * @param input
     *            原始字符串
     * @param enc
     *            字符集编码
     * @return 编码后的字符串
     */
    public static String encode(String input, String enc) {
        if (input == null)
            return null;
        try {
            return new String(encode(input.getBytes(enc)));
        } catch (UnsupportedEncodingException e) {
            return encode(input);
        }
    }
    
    /**
     * 带字符编码方式的XBase64解码操作.
     * 
     * @param data
     *            XBase64编码字符串
     * @param enc
     *            字符集编码
     * @return 解码后的字符串
     */
    public static String decode(String data, String enc) {
        byte[] b = decode(data.getBytes());
        try {
            return new String(b, enc);
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }
    
    /**
     * 解码操作.
     * 
     * @param data
     *            编码字符串
     * @return 解码后字符串
     */
    public static String decode(String data) {
        byte[] b = decode(data.getBytes());
        return new String(b);
    }
    
    /**
     * 解码操作.
     * 
     * @param data
     * @return
     */
    public static byte[] decode(byte[] data) {
        if (data == null)
            return null;
        if (data.length == 0)
            return new byte[0];
            
        byte b1, b2, b3, b4;
        int end = data.length;
        int i = 0;
        int finish = end - 4;
        
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUF_SIZE);
        while (i < finish) {
            b1 = decodingTable[data[i++]];
            b2 = decodingTable[data[i++]];
            b3 = decodingTable[data[i++]];
            b4 = decodingTable[data[i++]];
            
            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);
        }
        
        if (data[end - 2] == padding) {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];
            
            out.write((b1 << 2) | (b2 >> 4));
        } else if (data[end - 1] == padding) {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];
            b3 = decodingTable[data[end - 2]];
            
            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
        } else {
            b1 = decodingTable[data[end - 4]];
            b2 = decodingTable[data[end - 3]];
            b3 = decodingTable[data[end - 2]];
            b4 = decodingTable[data[end - 1]];
            
            out.write((b1 << 2) | (b2 >> 4));
            out.write((b2 << 4) | (b3 >> 2));
            out.write((b3 << 6) | b4);
        }
        
        byte result[] = out.toByteArray();
        return result;
    }
}