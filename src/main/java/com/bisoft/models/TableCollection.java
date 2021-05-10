package com.bisoft.models;

import com.bisoft.interfaces.*;

import java.io.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCollection implements ITableCollection {
    private final Connection connection;
    private final String query;

    public TableCollection(Connection connection, String query) {
      this.connection = connection;
      this.query = query;
    }

    @Override
    public void save(IClearedFolder folder, IFileFormat format) throws IOException, SQLException {
        String queryColumns = (new ResourceFile("get_table_content.sql")).content();

        ResultSet tables = connection.createStatement().executeQuery(query);
        while (tables.next()) {
            String tableName = tables.getString("table_name");
            String contentQuery = String.format(queryColumns, tableName);
            ITableContent table = new TableContent(connection, tableName);


            format.saveStart((Path.of(folder.toString(), tableName)).toFile());
            table.save(new DBSource(connection, contentQuery), (ISavedFormat) format);
            format.saveEnd();
        }
    }

}
