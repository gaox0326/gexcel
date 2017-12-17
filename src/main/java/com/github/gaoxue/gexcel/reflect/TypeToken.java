package com.github.gaoxue.gexcel.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author gaoxue
 */
public class TypeToken<T> {

    @Getter
    @Setter
    private Type type;

    @Getter
    @Setter
    private Class<? super T> rawType;

    @SuppressWarnings("unchecked")
    public TypeToken() {
        type = getSuperclassTypeParameter(getClass());
        rawType = (Class<? super T>) getRawType(type);
    }

    @SuppressWarnings("unchecked")
    private TypeToken(Type type) {
        this.type = getType(type);
        rawType = (Class<? super T>) getRawType(this.type);
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return getType(parameterized.getActualTypeArguments()[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeToken<?>)) {
            return false;
        }
        Type objType = ((TypeToken<?>) obj).getType();
        if (type == objType) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(objType);
        }
        // ParameterrizedType GenericArrayType WildcardType TypeVariable
        return false;
    }

    private static Type getType(Type type) {
        if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isArray()) {
                return getType(clazz.getComponentType());
            }
        } else if (type instanceof GenericArrayType) {
            return getType(((GenericArrayType) type).getGenericComponentType());
        }
        return type;
    }

    private static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            return clazz;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type rawType = getType(pType.getRawType());
            return (Class<?>) rawType;
        } else if (type instanceof GenericArrayType) {
            GenericArrayType gType = (GenericArrayType) type;
            Type rawType = gType.getGenericComponentType();
            return Array.newInstance(getRawType(rawType), 0).getClass();
        } else if (type instanceof WildcardType) {
            WildcardType wType = (WildcardType) type;
            return getRawType(wType.getUpperBounds()[0]);
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or " + "GenericArrayType, but <" + type + "> is of type " + className);
        }
    }

    public static <T> TypeToken<T> get(Class<T> classOfT) {
        return new TypeToken<T>(classOfT);
    }

    public static TypeToken<Object> get(Type type) {
        return new TypeToken<Object>(type);
    }

}
