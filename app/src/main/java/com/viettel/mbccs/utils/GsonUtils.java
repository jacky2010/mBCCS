package com.viettel.mbccs.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class GsonUtils {

    public static Gson instance;

    public static Gson getInstance() {
        if (instance == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                @Override
                public JsonElement serialize(Double originalValue, Type typeOf,
                        JsonSerializationContext context) {
                    BigDecimal bigValue = BigDecimal.valueOf(originalValue);

                    return new JsonPrimitive(bigValue.toPlainString());
                }
            });

            gsonBuilder.excludeFieldsWithoutExposeAnnotation();
            instance = gsonBuilder.create();
        }

        return instance;
    }

    public static String Object2String(Object obj) {

        return getInstance().toJson(obj);
    }

    public static <T> T String2Object(String json, Class<T> clzz) {
        return getInstance().fromJson(json, clzz);
    }

    public static <T> T String2Object(String json, Type typeOfT) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(json, typeOfT);
    }

    public static <T> List<T> String2ListObject(String json, Class<T[]> clazz) {
        T[] t = getInstance().fromJson(json, clazz);
        return Arrays.asList(t);
    }

    public static <T> T String2Object(String json, TypeToken<T> tTypeToken) {
        return getInstance().fromJson(json, tTypeToken.getType());
    }
}

