package com.bisoft.models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class TableBody implements Iterable<List<String>> {
    private final ResultSet result;

    public TableBody(ResultSet result) {
        this.result = result;
    }

    @Override
    public Iterator<List<String>> iterator() {
        try {
            ResultSetMetaData meta = result.getMetaData();
            return new BodyIterator(result, meta.getColumnCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BodyIterator(result, 0);
    }

}

class BodyIterator implements Iterator<List<String>> {
    private final ResultSet result;
    private final int columnCount;

    // constructor
    BodyIterator(ResultSet result, int columnCount) {
        this.result = result;
        this.columnCount = columnCount;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        try {
            return result.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // moves the cursor/iterator to next element
    public List<String> next() {
        List<String> cols = new ArrayList<>();
		for(int i=1; i<=columnCount; i++){
            try {
                cols.add(result.getString(i));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new NoSuchElementException("Iteration has no more elements");
            }
        }
		return cols;
    }

    // Used to remove an element. Implement only if needed
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
