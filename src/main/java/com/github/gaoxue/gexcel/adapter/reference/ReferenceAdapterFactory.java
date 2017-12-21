package com.github.gaoxue.gexcel.adapter.reference;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.gaoxue.gexcel.ExcelConfig;
import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * Reference adapters factory.
 * 
 * @author gaoxue
 */
public final class ReferenceAdapterFactory {

    private static final Map<TypeToken<?>, TypeAdapter<?>> adapterCache = new ConcurrentHashMap<TypeToken<?>, TypeAdapter<?>>();

    /**
     * Return adapter of specified reference type.
     * @param typeToken reference type token
     * @return adapter
     */
    @SuppressWarnings("unchecked")
    public static <T> TypeAdapter<T> create(TypeToken<T> typeToken, ExcelConfig config) {
        if (adapterCache.containsKey(typeToken)) {
            return (TypeAdapter<T>) adapterCache.get(typeToken);
        }
        TypeAdapter<?> typeAdapter;
        Class<? super T> clazz = typeToken.getRawType();
        if (Collection.class.isAssignableFrom(clazz)) {
            typeAdapter = CollectionAdapter.create(typeToken, config);
        } else if (clazz.equals(String.class)) {
            typeAdapter = new StringAdapter();
        } else if (clazz.equals(Date.class)) {
            typeAdapter = new DateAdapter(config.getDateFormat());
        } else {
            typeAdapter = ReflectiveObjectAdapter.create(typeToken, config);
        }
        if (typeAdapter != null) {
            adapterCache.put(typeToken, typeAdapter);
        }
        return (TypeAdapter<T>) typeAdapter;
    }
}
