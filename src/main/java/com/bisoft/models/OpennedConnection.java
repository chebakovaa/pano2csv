package com.bisoft.models;

import com.bisoft.interfaces.IOpennedConnection;

import java.sql.Connection;

public class OpennedConnection implements IOpennedConnection {
    private final Connection connection;

    public OpennedConnection(Connection connection) {
        this.connection = connection;
    }
}
