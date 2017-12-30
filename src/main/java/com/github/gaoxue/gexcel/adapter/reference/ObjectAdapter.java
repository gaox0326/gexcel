package com.github.gaoxue.gexcel.adapter.reference;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * {@code Object} adapter.
 * <p>Value type
 * <ul>
 *   <li>{@code Boolean}</li>
 *   <li>{@code Date}</li>
 *   <li>{@code Double}</li>
 *   <li>{@code String}</li>
 * </ul>
 * @author gaoxue
 */
public class ObjectAdapter implements TypeAdapter<Object> {

    public Object read(Reader reader) {
        return reader.readObject();
    }

}
