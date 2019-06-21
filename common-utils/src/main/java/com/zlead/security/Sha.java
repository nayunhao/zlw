package com.zlead.security;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;


/**
 * Sha编码
 *
 */
public class Sha
{
    public static final String ALGORITHM_SHA1 = "SHA1";
    public static final String ALGORITHM_SHA224 = "SHA224";
    public static final String ALGORITHM_SHA256 = "SHA256";
    public static final String ALGORITHM_SHA384 = "SHA384";
    public static final String ALGORITHM_SHA512 = "SHA512";

    /**
     * Sha编码
     *
     * @param data      待加密字节数组
     * @param algorithm Sha算法
     * @return 加密后16机制字符串
     */
    public static String encode(byte[] data, String algorithm)
    {
        Digest digest;
        if (StringUtils.equals(algorithm, "SHA1")) digest = new SHA1Digest();
        else if (StringUtils.equals(algorithm, "SHA224")) digest = new SHA224Digest();
        else if (StringUtils.equals(algorithm, "SHA256")) digest = new SHA256Digest();
        else if (StringUtils.equals(algorithm, "SHA384")) digest = new SHA384Digest();
        else if (StringUtils.equals(algorithm, "SHA512")) digest = new SHA512Digest();
        else digest = new SHA1Digest();

        byte[] resBuf = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(resBuf, 0);
        return Hex.encode(resBuf);
    }

    /**
     * Sha编码
     *
     * @param data 待加密字符串
     * @return 加密后16机制字符串
     */
    public static String encode(String data)
    {
        return encode(data.getBytes(), ALGORITHM_SHA1);
    }

    public static void main(String[] args) throws CodecException
    {
        System.out.println(Sha.encode("111111"));
    }
}
