/*
 * @(#)Base128.java Copyright BeiJing PingXin Tech Co.,Ltd. All Rights Reserved.
 */
package com.zlead.security;

import com.zlead.utils.BytesReader;

/**
 * Base128
 * 
 * @author Alan Yuan
 * @version 1.0
 * @see
 */
public final class Base128 {
    
    private final static byte[] encodingTable = new byte[128];
    private static byte         padding       = (byte) 0x20;
                                              
    static {
        // encodingTable[0] = -128 ;
        for (int i = 1; i < 128; i++) {
            encodingTable[i] = (byte) (-1 * i);
        }
    }
    
    /**
     *
     */
    private Base128() {
    }
    
    /**
     * 
     * @param data
     * @return
     */
    public static byte[] encode(byte[] data) {
        if (data == null)
            return null;
        if (data.length == 0)
            return new byte[0];
            
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
            
        BytesReader reader = new BytesReader(1024);
        
        int modulus = length % 7;
        int dataLength = (length - modulus);
        
        int a1, a2, a3, a4, a5, a6, a7;
        for (int i = offset; i < dataLength; i += 7) {
            a1 = data[i] & 0xFF;
            a2 = data[i + 1] & 0xFF;
            a3 = data[i + 2] & 0xFF;
            a4 = data[i + 3] & 0xFF;
            a5 = data[i + 4] & 0xFF;
            a6 = data[i + 5] & 0xFF;
            a7 = data[i + 6] & 0xFF;
            
            reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
            reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
            reader.append(encodingTable[((a2 << 5) | (a3 >>> 3)) & 0x7F]);
            reader.append(encodingTable[((a3 << 4) | (a4 >>> 4)) & 0x7F]);
            reader.append(encodingTable[((a4 << 3) | (a5 >>> 5)) & 0x7F]);
            reader.append(encodingTable[((a5 << 2) | (a6 >>> 6)) & 0x7F]);
            reader.append(encodingTable[((a6 << 1) | (a7 >>> 7)) & 0x7F]);
            reader.append(encodingTable[a7 & 0x7F]);
        }
        //
        switch (modulus) {
            case 1:
                a1 = data[dataLength] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[(a1 << 6) & 0x7F]);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                break;
            case 2:
                a1 = data[dataLength] & 0xFF;
                a2 = data[dataLength + 1] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
                reader.append(encodingTable[(a2 << 5) & 0x7F]);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                break;
            case 3:
                a1 = data[dataLength] & 0xFF;
                a2 = data[dataLength + 1] & 0xFF;
                a3 = data[dataLength + 2] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
                reader.append(encodingTable[((a2 << 5) | (a3 >>> 3)) & 0x7F]);
                reader.append(encodingTable[((a3 << 4)) & 0x7F]);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                break;
            case 4:
                a1 = data[dataLength] & 0xFF;
                a2 = data[dataLength + 1] & 0xFF;
                a3 = data[dataLength + 2] & 0xFF;
                a4 = data[dataLength + 3] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
                reader.append(encodingTable[((a2 << 5) | (a3 >>> 3)) & 0x7F]);
                reader.append(encodingTable[((a3 << 4) | (a4 >>> 4)) & 0x7F]);
                reader.append(encodingTable[(a4 << 3) & 0x7F]);
                reader.append(padding);
                reader.append(padding);
                reader.append(padding);
                break;
            case 5:
                a1 = data[dataLength] & 0xFF;
                a2 = data[dataLength + 1] & 0xFF;
                a3 = data[dataLength + 2] & 0xFF;
                a4 = data[dataLength + 3] & 0xFF;
                a5 = data[dataLength + 4] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
                reader.append(encodingTable[((a2 << 5) | (a3 >>> 3)) & 0x7F]);
                reader.append(encodingTable[((a3 << 4) | (a4 >>> 4)) & 0x7F]);
                reader.append(encodingTable[((a4 << 3) | (a5 >>> 5)) & 0x7F]);
                reader.append(encodingTable[(a5 << 2) & 0x7F]);
                reader.append(padding);
                reader.append(padding);
                break;
            case 6:
                a1 = data[dataLength] & 0xFF;
                a2 = data[dataLength + 1] & 0xFF;
                a3 = data[dataLength + 2] & 0xFF;
                a4 = data[dataLength + 3] & 0xFF;
                a5 = data[dataLength + 4] & 0xFF;
                a6 = data[dataLength + 5] & 0xFF;
                reader.append(encodingTable[(a1 >>> 1) & 0x7F]);
                reader.append(encodingTable[((a1 << 6) | (a2 >>> 2)) & 0x7F]);
                reader.append(encodingTable[((a2 << 5) | (a3 >>> 3)) & 0x7F]);
                reader.append(encodingTable[((a3 << 4) | (a4 >>> 4)) & 0x7F]);
                reader.append(encodingTable[((a4 << 3) | (a5 >>> 5)) & 0x7F]);
                reader.append(encodingTable[((a5 << 2) | (a6 >>> 6)) & 0x7F]);
                reader.append(encodingTable[(a6 << 1) & 0x7F]);
                reader.append(padding);
                break;
            default:
                break;
        }
        //
        byte result[] = reader.getBytes();
        reader.close();
        return result;
    }
    
    /**
     * 
     * @param data
     * @return
     */
    public static byte[] decode(byte[] data) {
        if (data == null)
            return null;
        if (data.length == 0)
            return new byte[0];
            
        int length = data.length;
        int finish = length - 8;
        BytesReader reader = new BytesReader(1024);
        if (data[length - 1] != padding)
            finish = length;
            
        byte[] input = new byte[8];
        for (int i = 0; i < finish;) {
            input[0] = (byte) (~data[i++] + 1);
            input[1] = (byte) (~data[i++] + 1);
            input[2] = (byte) (~data[i++] + 1);
            input[3] = (byte) (~data[i++] + 1);
            input[4] = (byte) (~data[i++] + 1);
            input[5] = (byte) (~data[i++] + 1);
            input[6] = (byte) (~data[i++] + 1);
            input[7] = (byte) (~data[i++] + 1);
            
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            input[2] = (byte) (input[2] << 3 | input[3] >> 4);
            input[3] = (byte) (input[3] << 4 | input[4] >> 3);
            input[4] = (byte) (input[4] << 5 | input[5] >> 2);
            input[5] = (byte) (input[5] << 6 | input[6] >> 1);
            input[6] = (byte) (input[6] << 7 | input[7]);
            //
            reader.append(input, 0, 7);
        }
        // do padding
        if (data[length - 6] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            //
            reader.append(input, 0, 1);
        } else if (data[length - 5] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[2] = (byte) (~data[length - 6] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            //
            reader.append(input, 0, 2);
        } else if (data[length - 4] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[2] = (byte) (~data[length - 6] + 1);
            input[3] = (byte) (~data[length - 5] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            input[2] = (byte) (input[2] << 3 | input[3] >> 4);
            //
            reader.append(input, 0, 3);
        } else if (data[length - 3] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[2] = (byte) (~data[length - 6] + 1);
            input[3] = (byte) (~data[length - 5] + 1);
            input[4] = (byte) (~data[length - 4] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            input[2] = (byte) (input[2] << 3 | input[3] >> 4);
            input[3] = (byte) (input[3] << 4 | input[4] >> 3);
            //
            reader.append(input, 0, 4);
        } else if (data[length - 2] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[2] = (byte) (~data[length - 6] + 1);
            input[3] = (byte) (~data[length - 5] + 1);
            input[4] = (byte) (~data[length - 4] + 1);
            input[5] = (byte) (~data[length - 3] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            input[2] = (byte) (input[2] << 3 | input[3] >> 4);
            input[3] = (byte) (input[3] << 4 | input[4] >> 3);
            input[4] = (byte) (input[4] << 5 | input[5] >> 2);
            //
            reader.append(input, 0, 5);
        } else if (data[length - 1] == padding) {
            input[0] = (byte) (~data[length - 8] + 1);
            input[1] = (byte) (~data[length - 7] + 1);
            input[2] = (byte) (~data[length - 6] + 1);
            input[3] = (byte) (~data[length - 5] + 1);
            input[4] = (byte) (~data[length - 4] + 1);
            input[5] = (byte) (~data[length - 3] + 1);
            input[6] = (byte) (~data[length - 2] + 1);
            input[0] = (byte) (input[0] << 1 | input[1] >> 6);
            input[1] = (byte) (input[1] << 2 | input[2] >> 5);
            input[2] = (byte) (input[2] << 3 | input[3] >> 4);
            input[3] = (byte) (input[3] << 4 | input[4] >> 3);
            input[4] = (byte) (input[4] << 5 | input[5] >> 2);
            input[5] = (byte) (input[5] << 6 | input[6] >> 1);
            //
            reader.append(input, 0, 2);
        }
        
        return reader.getBytes();
    }
}
