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

    Object readObject();

    void skip();

    // isNextNull()
}
