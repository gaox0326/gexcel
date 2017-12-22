package com.github.gaoxue.gexcel.adapter.reference;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.gaoxue.common.AnnotationUtil;
import com.github.gaoxue.common.StringUtil;
import com.github.gaoxue.gexcel.adapter.AdapterFactory;
import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;
import com.github.gaoxue.gexcel.reflect.TypeToken;
import com.github.gaoxue.gexcel.template.Column;
import com.github.gaoxue.gexcel.template.Metedata;

import lombok.Getter;
import lombok.Setter;

/**
 * Reflective object adapter.
 * 
 * @author gaoxue
 */
public class ReflectiveObjectAdapter<T> implements TypeAdapter<T> {

    private TypeToken<T> typeToken;

    private Map<String, ReflectiveField> fieldMap;

    @Getter
    @Setter
    private Boolean isMain = true;

    public T read(Reader reader) {
        try {
            if (!reader.hasNext()) {
                return null;
            }
            @SuppressWarnings("unchecked")
            T result = (T) typeToken.getRawType().newInstance();
            if (getIsMain()) {
                reader.beginObject();
            }
            while (reader.hasNext()) {
                String name = "";
                Metedata metedata = reader.readNextMeteData();
                if (metedata != null) {
                    name = metedata.getName();
                }
                if (!fieldMap.containsKey(name)) {
                    reader.skip();
                    continue;
                }
                ReflectiveField field = fieldMap.get(name);
                field.read(reader, result);
            }
            if (getIsMain()) {
                reader.endObject();
            }
            return result;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReflectiveObjectAdapter(TypeToken<T> typeToken) {
        this.typeToken = typeToken;
    }

    public static <T> ReflectiveObjectAdapter<T> create(TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        ReflectiveObjectAdapter<T> result = new ReflectiveObjectAdapter<T>(typeToken);
        result.fieldMap = getFieldMap(typeToken);
        return result;
    }

    private static <T> Map<String, ReflectiveField> getFieldMap(TypeToken<T> typeToken) {
        Map<String, ReflectiveField> fieldMap = new HashMap<String, ReflectiveField>();
        List<Field> fieldList = AnnotationUtil.getFieldAnnotated(typeToken.getRawType(), Column.class);
        for (Field field : fieldList) {
            Column column = field.getAnnotation(Column.class);
            String name = column.name();
            if (StringUtil.isNullOrEmpty(name)) {
                name = field.getName();
            }
            Class<?> type = field.getType();
            TypeAdapter<?> typeAdapter = AdapterFactory.create(TypeToken.get(type));
            if (typeAdapter instanceof ReflectiveObjectAdapter) {
                // TODO association
                ((ReflectiveObjectAdapter<?>) typeAdapter).setIsMain(false);
            }
            fieldMap.put(name, new ReflectiveField(field, typeAdapter));
        }
        return fieldMap;
    }

    protected static class ReflectiveField {

        private Field field;

        private TypeAdapter<?> delegate;

        public ReflectiveField(Field field, TypeAdapter<?> typeAdapter) {
            this.field = field;
            this.delegate = typeAdapter;
        }

        public void read(Reader reader, Object value) {
            Object fieldValue = delegate.read(reader);
            try {
                field.setAccessible(true);
                field.set(value, fieldValue);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                // should not happen
                throw new ExcelParseException(ex);
            }
        }

    }

}
