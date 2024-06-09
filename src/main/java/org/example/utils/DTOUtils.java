package org.example.utils;

import java.lang.reflect.Field;

public class DTOUtils {

    // returns true if all fields are null
    public static boolean isAllFieldsNull(Object obj) {
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(obj) != null) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
