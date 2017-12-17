package com.github.gaoxue.gexcel.adapter.reference;

import com.github.gaoxue.gexcel.adapter.TypeAdapter;
import com.github.gaoxue.gexcel.reader.Reader;

/**
 * String adapter.
 * 
 * @author gaoxue
 */
public class StringAdapter implements TypeAdapter<String> {

    public String read(Reader reader) {
        return reader.readString();
    }

}
