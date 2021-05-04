package com.bisoft.models;

import com.bisoft.interfaces.IContentSource;
import com.bisoft.interfaces.ITableSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public final class DBSource implements ITableSource {

    private final String contentQuery;
    private final Connection connection;

    public DBSource(Connection connection, String contentQuery){
        this.contentQuery = contentQuery;
        this.connection = connection;
    }

    @Override
    public IContentSource content() throws SQLException {
        ResultSet resultRows = connection.createStatement().executeQuery(contentQuery);
        ResultSetMetaData meta = resultRows.getMetaData();
        return new ContentSource(resultRows, meta.getColumnCount());
    }

}
