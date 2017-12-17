package com.github.gaoxue.gexcel.adapter.reference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.github.gaoxue.gexcel.adapter.AdapterFactory;
import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.constructor.ConstructorFactory;
import com.github.gaoxue.gexcel.constructor.ObjectConstructor;
import com.github.gaoxue.gexcel.exception.ExcelParseException;
import com.github.gaoxue.gexcel.reader.Reader;
import com.github.gaoxue.gexcel.reflect.TypeToken;

/**
 * {@code Collection} adapter.
 * 
 * @author gaoxue
 */
public class CollectionAdapter<T> implements TypeAdapter<Collection<T>> {

    /** {@code Collection} component type adapter */
    private TypeAdapter<T> elementTypeAdapter;

    /** {@code Collection} constructor */
    private ObjectConstructor<Collection<T>> constructor;

    private CollectionAdapter(TypeToken<Collection<T>> typeToken, TypeAdapter<T> elementTypeAdapter) {
        this.elementTypeAdapter = elementTypeAdapter;
        constructor = ConstructorFactory.get(typeToken);
    }

    @Override
    public Collection<T> read(Reader reader) {
        Collection<T> result = constructor.construct();
        if (!reader.hasNext()) {
            return result;
        }
        reader.beginArray();
        while (reader.hasNext()) {
            T element = elementTypeAdapter.read(reader);
            result.add(element);
        }
        reader.endArray();
        return result;
    }

    public static <E> CollectionAdapter<E> create(TypeToken<E> typeToken) {
        Class<? super E> rawType = typeToken.getRawType();
        if (!Collection.class.isAssignableFrom(rawType)) {
            return null;
        }
        if (!List.class.isAssignableFrom(rawType)) {
            throw new ExcelParseException("Support List only, for now.");
        }

        Type componnetType = getComponentType(typeToken);
        TypeToken<Object> componnetTypeToken = TypeToken.get(componnetType);
        @SuppressWarnings({ "unchecked", "rawtypes" })
        CollectionAdapter<E> result = new CollectionAdapter(typeToken, AdapterFactory.create(componnetTypeToken));
        return result;
    }

    private static <E> Type getComponentType(TypeToken<E> typeToken) {
        // ParameterizedType List<Person>
        Type type = typeToken.getType();
        if (type instanceof ParameterizedType) {
            return ((ParameterizedType) type).getActualTypeArguments()[0];
        } else {
            throw new ExcelParseException("Unable to handle type, " + type.getTypeName() + ".");
        }
    }
}
