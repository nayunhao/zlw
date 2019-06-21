package com.zlead.utils.gsonutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PerfectGson {

    public static Gson getGson() {
        return new GsonBuilder().serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>())
                .setExclusionStrategies(new AnnotationExclusion()).create();
    }

    public static Gson getGson(String[] strs) {
        return new GsonBuilder().serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory<Object>())
                .setExclusionStrategies(new FieldExclusion(strs)).create();
    }

}
