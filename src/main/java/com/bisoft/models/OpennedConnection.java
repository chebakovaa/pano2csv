package com.bisoft.models;

import com.bisoft.interfaces.IOpennedConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OpennedConnection implements IOpennedConnection {
    private final Connection connection;

    public OpennedConnection(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public ResultSet Query(String collectionQuery) throws SQLException {
        return connection.createStatement().executeQuery(collectionQuery);
    }
}
