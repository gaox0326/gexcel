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

    String readString();

    Boolean readBoolean();

    Double readDouble() throws NumberFormatException;

    Object readObject();

    void skip();

    // isNextNull()
}
