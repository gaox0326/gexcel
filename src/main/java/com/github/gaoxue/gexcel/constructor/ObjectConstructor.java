package com.github.gaoxue.gexcel.constructor;

/**
 * Object constructor interface.
 * @author gaoxue
 */
public interface ObjectConstructor<T> {

    /**
     * Returns a new instance.
     * @return
     */
    T construct();

}
