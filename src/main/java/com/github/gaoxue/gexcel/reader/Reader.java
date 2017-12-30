package com.github.gaoxue.gexcel.reader;

import com.github.gaoxue.gexcel.template.Metedata;

/**
 * @author gaoxue
 */
public interface Reader {

    void beginArray();

    void endArray();

    void beginObject();

    void endObject();

    boolean hasNext();

    Metedata readNextMeteData();

    /**
     * Get Object value.
     * @return Object value
     */
    Object readObject();

    void skip();

    // isNextNull()
}
