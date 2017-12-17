package com.github.gaoxue.gexcel.adapter;

import com.github.gaoxue.gexcel.reader.Reader;

/**
 * Type adapter interface, convert excel into Java object
 * 
 * @author gaoxue
 */
public interface TypeAdapter<T> {

    /**
     * Reads one excel value (an array, object, string, number, boolean or null)
     * and converts it into a Java object. Returns the converted object.
     * 
     * @param reader
     * @return the converted Java Object
     */
    T read(Reader reader);

}
