package com.github.gaoxue.gexcel;

import java.lang.reflect.Type;

import com.github.gaoxue.gexcel.adapter.AdapterFactory;
import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.config.ExcelConfig;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * @author gaoxue
 */
public class Excel {

    public static <T> T fromExcel(ExcelConfig config, Class<T> classOfT) {
        TypeToken<T> typeToken = TypeToken.get(classOfT);
        TypeAdapter<T> adapter = AdapterFactory.create(typeToken);
        return adapter.read(config.getReader());
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromExcel(ExcelConfig config, Type typeOfT) {
        TypeToken<Object> typeToken = TypeToken.get(typeOfT);
        TypeAdapter<?> adapter = AdapterFactory.create(typeToken);
        return (T) adapter.read(config.getReader());
    }

}
