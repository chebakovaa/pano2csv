package com.bisoft.models;

import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISavedFormat;
import com.bisoft.interfaces.ITableCollection;
import com.bisoft.interfaces.ITableContent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableCollection implements ITableCollection {
    private final Connection connection;
    private final String query;

    public TableCollection(Connection connection, String query) {
      this.connection = connection;
      this.query = query;
    }

    @Override
    public void save(File folder, IFileFormat format) throws IOException, SQLException {
        ResultSet tables = connection.createStatement().executeQuery(query);
        while (true) {
            if (!tables.next()) break;
            String tableName = tables.getString("table_name");
            //TODO take out sql query into special file
            String contentQuery = String.format("select * from neo.%s", tableName);
            ITableContent table = new TableContent(connection, tableName);
            format.saveStart((Path.of(folder.toString(), tableName)).toFile());
            table.save(connection.createStatement().executeQuery(query), (ISavedFormat) format);
            format.saveEnd();
        }
    }
}
