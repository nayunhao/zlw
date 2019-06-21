package com.zlead.security;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * HMacMd5编码
 *
 */
public class HmacMd5
{
    /**
     * HMacMd5编码
     *
     * @param data 待加密字节数组
     * @param key  加密私钥
     * @return 加密后字符串
     */
    public static String encode(byte[] data, String key)
    {
        Digest digest = new MD5Digest();
        HMac hMac = new HMac(new MD5Digest());
        KeyParameter keyParameter = new KeyParameter(key.getBytes());
        hMac.init(keyParameter);
        byte[] resBuf = new byte[digest.getDigestSize()];
        hMac.update(data, 0, data.length);
        hMac.doFinal(resBuf, 0);
        return Hex.encode(resBuf);
    }

    /**
     * HMacMd5编码
     *
     * @param data 待加密字符串
     * @param key  加密私钥
     * @return 加密后字符串
     */
    public static String encode(String data, String key)
    {
        return encode(data.getBytes(), key);
    }

    public static void main(String[] args)
    {
        System.out.println(HmacMd5.encode("zz", "zz"));
        System.out.println(HmacMd5.encode("zz".getBytes(), "zz"));
    }
}
