package com.zlead.utils.gsonutil;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class FieldExclusion implements ExclusionStrategy {
    String[] inStr;

    public FieldExclusion(String[] outStr) {
        this.inStr = outStr;
    }

    public boolean shouldSkipClass(Class<?> class1) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        boolean flag = false;

        for (int i = 0; i < inStr.length; i++) {
            // 手动排除inStr串指定字段
            if (f.getName().equals(inStr[i])) {
                flag = true;
                System.out.println("排除字段===============>" + f.getName());
            } else {
                // 如果属性带有MyExclus 注解，则排除
                flag = f.getAnnotation(MyExclus.class) != null;
            }
        }

        return flag;
    }


}
