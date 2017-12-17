package com.github.gaoxue.gexcel.adapter.primitive;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * Primitive adapters factory
 * 
 * @author gaoxue
 */
public final class PrimitiveAdapterFactory {

    /** cache of primitive adapters */
    private static final Map<TypeToken<?>, TypeAdapter<?>> adapterCache = new ConcurrentHashMap<TypeToken<?>, TypeAdapter<?>>();

    /**
     * Return adapter of specified primitive type.
     * @param typeToken primitive type token
     * @return adapter
     */
    @SuppressWarnings("unchecked")
    public static <T> TypeAdapter<T> create(TypeToken<T> typeToken) {
        if (adapterCache.containsKey(typeToken)) {
            return (TypeAdapter<T>) adapterCache.get(typeToken);
        }
        TypeAdapter<?> adapter = null;
        Class<? super T> clazz = typeToken.getRawType();
        if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            adapter = BooleanAdapter.BOOLEAN;
        } else if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            adapter = IntegralTypeAdapters.BYTE;
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            adapter = IntegralTypeAdapters.SHORT;
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            adapter = IntegralTypeAdapters.INTEGER;
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            adapter = IntegralTypeAdapters.LONG;
        } else if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            adapter = IntegralTypeAdapters.CHARACTER;
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            adapter = IntegralTypeAdapters.DOUBLE;
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            adapter = IntegralTypeAdapters.FLOAT;
        }
        if (adapter != null) {
            adapterCache.put(typeToken, adapter);
        }
        return (TypeAdapter<T>) adapter;
    }

}
