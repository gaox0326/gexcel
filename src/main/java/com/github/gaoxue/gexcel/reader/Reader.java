package com.github.gaoxue.gexcel.reader;

import com.github.gaoxue.gexcel.template.Metedata;

/**
 * @author gaoxue
 */
public interface Reader {

    /**
     * Begin read array
     */
    void beginArray();

    /**
     * End read array, must after {@link #beginArray()}
     */
    void endArray();

    /**
     * Begin read object
     */
    void beginObject();

    /**
     * End read object, must after {@link #beginObject()}
     */
    void endObject();

    /**
     * Has next value or not
     * @return
     */
    boolean hasNext();

    Metedata readNextMeteData();

    /**
     * Get Object value
     * @return Object value
     */
    Object readObject();

    /**
     * Skip read value
     */
    void skip();

    // isNextNull()
}
