package com.bisoft;


import com.bisoft.helpers.ClearFolderContentExeption;
import com.bisoft.helpers.DBConnectionExeption;
import com.bisoft.interfaces.*;
import com.bisoft.models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.bisoft.helpers.SqlHelper.getConnection;

public class App
{
    public static void main(String[] args)
    {
        System.out.println( "Start!" );
        String delimiter = ";";
        File folder = args[0] != null && args[0].length() > 0 ? new File(args[0]): new File(Paths.get(System.getProperty("user.home"), "neo4j\\import\\pitc").toUri());

        try {
            IClearedFolder clearedFolder = new FolderContent(folder).clearedFolder();
            IAppConnection appConnection = new AppConnection(new AppResource("db.properties").loadedProperties());
            IOpennedConnection openedConnection = appConnection.opennedConnection();

            ITableCollection tc = new TableCollection(openedConnection, queryResources);
            tc.save(clearedFolder, new CSVFormat(delimiter));
        } catch (ClearFolderContentExeption clearFolderContentExeption) {
            clearFolderContentExeption.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DBConnectionExeption dbConnectionExeption) {
            dbConnectionExeption.printStackTrace();
        }
    }
    
    private static void SaveCSV() {

        Properties property = new Properties();
        InputStream is = App.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            property.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        InputStream queryStream = App.class.getClassLoader().getResourceAsStream("get_all_tables.sql");
        String queryTables = new BufferedReader(
          new InputStreamReader(queryStream, StandardCharsets.UTF_8))
            .lines()
          .collect(Collectors.joining("\n"));
    


        try {
            folderContent.clear();
            ITableCollection tc = new TableCollection(connection, queryTables);
            tc.save(folder, new CSVFormat(delimiter));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClearFolderContentExeption e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
