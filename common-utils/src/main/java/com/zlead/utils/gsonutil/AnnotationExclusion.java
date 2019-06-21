package com.zlead.utils.gsonutil;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class AnnotationExclusion implements ExclusionStrategy {

    @Override
    public boolean shouldSkipClass(Class<?> class1) {
        return false;
    }


    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        //如果属性带有MyExclus 注解，则排除
        return f.getAnnotation(MyExclus.class) != null;
    }

}
