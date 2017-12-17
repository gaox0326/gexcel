package com.github.gaoxue.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Annotation utility
 * 
 * @author gaoxue
 */
public final class AnnotationUtil {

    /** cache of fields with key Class */
    private static Map<Class<?>, List<Field>> fieldCache = new HashMap<>();

    /**
     * Return fields annotated with {@code annotationClass}.
     * @param clazz source class
     * @param annotationClass annotation class
     * @return
     */
    public static List<Field> getFieldAnnotated(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        if (fieldCache.containsKey(clazz)) {
            return fieldCache.get(clazz);
        }
        List<Field> resultList = new ArrayList<>();
        Class<?> temp = clazz;
        while (temp != Object.class) {
            Field[] fields = temp.getDeclaredFields();
            for (Field field : fields) {
                boolean hasAnnotation = field.isAnnotationPresent(annotationClass);
                if (hasAnnotation) {
                    resultList.add(field);
                }
            }
            temp = temp.getSuperclass();
        }
        fieldCache.put(clazz, resultList);
        return resultList;
    }

    private AnnotationUtil() {
        // final utility class, avoid instantiate
    }

}
