package com.zlead.security;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.*;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * HMacSha编码
 */
public class HmacSha
{
    public static final String ALGORITHM_SHA1 = "SHA1";
    public static final String ALGORITHM_SHA224 = "SHA224";
    public static final String ALGORITHM_SHA256 = "SHA256";
    public static final String ALGORITHM_SHA384 = "SHA384";
    public static final String ALGORITHM_SHA512 = "SHA512";

    /**
     * HMacSha编码
     *
     * @param data      待加密字节数组
     * @param key       加密私钥
     * @param algorithm Sha算法
     * @return 加密后字符串
     */
    public static String encode(byte[] data, String key, String algorithm)
    {
        Digest digest;
        if (StringUtils.equals(algorithm, "SHA1")) digest = new SHA1Digest();
        else if (StringUtils.equals(algorithm, "SHA224")) digest = new SHA224Digest();
        else if (StringUtils.equals(algorithm, "SHA256")) digest = new SHA256Digest();
        else if (StringUtils.equals(algorithm, "SHA384")) digest = new SHA384Digest();
        else if (StringUtils.equals(algorithm, "SHA512")) digest = new SHA512Digest();
        else digest = new SHA1Digest();

        HMac hMac = new HMac(digest);
        KeyParameter keyParameter = new KeyParameter(key.getBytes());
        hMac.init(keyParameter);
        byte[] resBuf = new byte[digest.getDigestSize()];
        hMac.update(data, 0, data.length);
        hMac.doFinal(resBuf, 0);
        return Hex.encode(resBuf);
    }

    /**
     * HMacSha编码
     *
     * @param data 待加密字节数组
     * @param key  加密私钥
     * @return 加密后字符串
     */
    public static String encode(byte[] data, String key)
    {
        return encode(data, key, ALGORITHM_SHA1);
    }

    /**
     * HMacSha编码
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
        System.out.println(HmacSha.encode("zz", "zz"));
        System.out.println(HmacSha.encode("zz".getBytes(), "zz"));
    }
}
