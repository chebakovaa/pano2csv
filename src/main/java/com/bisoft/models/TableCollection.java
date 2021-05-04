package com.bisoft.models;

import com.bisoft.App;
import com.bisoft.interfaces.IFileFormat;
import com.bisoft.interfaces.ISavedFormat;
import com.bisoft.interfaces.ITableCollection;
import com.bisoft.interfaces.ITableContent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class TableCollection implements ITableCollection {
    private final Connection connection;
    private final String query;

    public TableCollection(Connection connection, String query) {
      this.connection = connection;
      this.query = query;
    }

    @Override
    public void save(File folder, IFileFormat format) throws IOException, SQLException {
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
