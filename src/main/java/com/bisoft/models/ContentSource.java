package com.bisoft.models;

import com.bisoft.interfaces.IContentSource;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ContentSource implements IContentSource {
    private final ResultSet result;
    private final int columnCount;

    public ContentSource(final ResultSet result, int columnCount) {
        this.result = result;
        this.columnCount = columnCount;
    }

    @Override
    public List<String> title() throws SQLException {
        ResultSetMetaData meta = result.getMetaData();
        List<String> row = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            row.add(meta.getColumnLabel(i));
        }
        return row;
    }

    @Override
    public Iterable<List<String>> body() {
        return new TableBody(result);
    }
}
