package com.zlead.utils;

import java.util.Random;
public class RandomUtil {
    /**
     * 生成N位随机数
     *
     */
    public static String getRandomCode(int n){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(n);
        for(int i=0;i<n;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
