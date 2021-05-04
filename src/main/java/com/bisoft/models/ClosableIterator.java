package com.bisoft.models;

import java.util.Iterator;

public interface ClosableIterator<T> extends Iterator<T>, AutoCloseable {

    void close();

}
