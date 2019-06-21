package com.zlead.utils;

import java.util.List;

public class ConvertByte {
	/**
	 * int to byte
	 * @param x
	 * @return
	 */
	public static byte intToByte(int x) {   
		return (byte) x;   
	}   
	/**
	 *  byte to int
	 * @param b
	 * @return
	 */
	public static int byteToInt(byte b) {   
		//Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值 
		return b & 0xFF;   
	} 
	/**
	 * byte[] to int
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {   
		return   b[3] & 0xFF |   
		            (b[2] & 0xFF) << 8 |   
		            (b[1] & 0xFF) << 16 |   
		            (b[0] & 0xFF) << 24;   
	}   
	/**
	 * int to byte[]
	 * @param a
	 * @return
	 */
	public static byte[] intToByteArray(int a) {   
		return new byte[] {   
		        (byte) ((a >> 24) & 0xFF),   
		        (byte) ((a >> 16) & 0xFF),      
		        (byte) ((a >> 8) & 0xFF),      
		        (byte) (a & 0xFF)   
		    };   
	} 
	/**
	 * 多个byte[] 组合成一个
	 * @param srcArrays
	 * @return
	 */
	public static byte[] sysCopy(List<byte[]> srcArrays) {
        int len = 0;
        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }
	/**
     * 截取byte数组
     * 
     * @param src
     * @param begin  开始位置
     * @param count   要截取的长度
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i = begin; i < begin + count; i++)
            bs[i - begin] = src[i];
        return bs;
    }
}
