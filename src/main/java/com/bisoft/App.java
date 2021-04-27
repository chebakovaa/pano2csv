package com.bisoft;


import com.bisoft.helpers.ClearFolderContentExeption;
import com.bisoft.interfaces.ITableCollection;
import com.bisoft.models.CSVFormat;
import com.bisoft.models.FolderContent;
import com.bisoft.models.TableCollection;
import com.bisoft.models.TableContent;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bisoft.helpers.SqlHelper.getConnection;

public class App
{
    public static void main(String[] args)
    {
        System.out.println( "Start!" );
        SaveCSV();
    }
    
    private static void SaveCSV() {
        File folder = new File(Paths.get(System.getProperty("user.home"), "neo4j\\import\\pitc").toUri());
        String delimiter = ";";
        FolderContent folderContent = new FolderContent(folder);
        
        final String DB_URL = "jdbc:postgresql://192.168.1.60:1105/pitc";
        final String USER = "postgres";
        final String PASS = "";
        Connection connection = getConnection(DB_URL, USER, PASS);
        if (connection == null) { return; }
        try {
            folderContent.clear();
            String query = "select table_name from INFORMATION_SCHEMA.views WHERE table_schema = 'neo' union \n" +
            "select table_name from INFORMATION_SCHEMA.tables WHERE table_schema = 'neo' AND table_type = 'BASE TABLE'";
            ITableCollection tc = new TableCollection(connection, query);
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
