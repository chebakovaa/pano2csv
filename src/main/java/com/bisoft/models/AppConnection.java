package com.bisoft.models;

import com.bisoft.helpers.ClearFolderContentExeption;
import com.bisoft.helpers.DBConnectionExeption;
import com.bisoft.interfaces.IAppConnection;
import com.bisoft.interfaces.IAppResource;
import com.bisoft.interfaces.IOpennedConnection;

import java.sql.Connection;
import java.util.Map;

import static com.bisoft.helpers.SqlHelper.getConnection;

public class AppConnection implements IAppConnection {
    private final Map<String, String> resource;

    public AppConnection(Map<String, String> resource) {
        this.resource = resource;
    }

    @Override
    public IOpennedConnection opennedConnection() throws DBConnectionExeption {

        Connection connection = getConnection(resource.dbURL(), resource.user(), resource.password());
        if (connection == null) {
            throw new DBConnectionExeption("DB connection fail");
        }
        return new OpennedConnection(connection);
    }
}
