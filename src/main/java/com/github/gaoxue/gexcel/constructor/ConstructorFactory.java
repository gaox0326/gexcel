package com.github.gaoxue.gexcel.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * Constructor factory.
 * 
 * @author gaoxue
 */
public final class ConstructorFactory {

    public static <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        ObjectConstructor<T> constructor = newDefaultObjectConstructor(rawType);
        if (constructor != null) {
            return constructor;
        }

        constructor = newDefaultImplementionConstructor(type, rawType);
        if (constructor != null) {
            return constructor;
        }

        throw new ExcelParseException("Unable to find constructor for " + rawType.getName() + ".");
    }

    private static <T> ObjectConstructor<T> newDefaultObjectConstructor(Class<? super T> rawType) {
        Constructor<? super T> constructor;
        try {
            constructor = rawType.getDeclaredConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return null;
        }
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return new ObjectConstructor<T>() {

            @SuppressWarnings("unchecked")
            @Override
            public T construct() {
                try {
                    return (T) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new ExcelParseException(ex);
                }
            }
        };
    }

    private static <T> ObjectConstructor<T> newDefaultImplementionConstructor(Type type, Class<? super T> rawType) {
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {

            } else if (EnumSet.class.isAssignableFrom(rawType)) {

            } else if (Set.class.isAssignableFrom(rawType)) {

            } else if (Queue.class.isAssignableFrom(rawType)) {

            } else if (List.class.isAssignableFrom(rawType)) {
                return new ObjectConstructor<T>() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public T construct() {
                        return (T) new ArrayList<>();
                    }
                };
            }
            return null;
        }

        if (Map.class.isAssignableFrom(rawType)) {
            return null;
        }
        return null;
    }

}
