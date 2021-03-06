package com.github.gaoxue.gexcel.adapter;

import com.github.gaoxue.gexcel.adapter.primitive.PrimitiveAdapterFactory;
import com.github.gaoxue.gexcel.adapter.reference.ReferenceAdapterFactory;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * Adapters factory.
 * 
 * @author gaoxue
 */
public class AdapterFactory {

    public static <T> TypeAdapter<T> create(TypeToken<T> typeToken) {
        TypeAdapter<T> typeAdapter = PrimitiveAdapterFactory.create(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        typeAdapter = ReferenceAdapterFactory.create(typeToken);
        if (typeAdapter == null) {
            throw new ExcelParseException("Class " + typeToken.getRawType().getName() + " can't find Adapter");
        }
        return typeAdapter;
    }

}
