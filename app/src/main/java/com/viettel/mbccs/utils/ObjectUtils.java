package com.viettel.mbccs.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by HuyQuyet on 6/17/17.
 */

public class ObjectUtils {

    public static <T, R> R convertObject(T data, Class<R> clss) {
        if (data == null) return null;
        try {
            Constructor<R> constructor = clss.getConstructor();
            R result = constructor.newInstance();
            for (Field field : clss.getDeclaredFields()) {
                field.setAccessible(true);
                for (Field f : data.getClass().getDeclaredFields()) {
                    f.setAccessible(true);
                    if (field.getName().equals(f.getName())) {
                        field.set(result, f.get(data));
                        break;
                    }
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
