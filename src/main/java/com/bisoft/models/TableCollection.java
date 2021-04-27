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
            String nameTable = tables.getString("table_name");
            ITableContent table = new TableContent(connection, nameTable);
            format.saveStart((Path.of(folder.toString(), nameTable)).toFile());
            table.save((ISavedFormat) format);
            format.saveEnd();
        }
    }
}
