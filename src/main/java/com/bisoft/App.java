package com.bisoft;

import com.bisoft.models.WorkFolder;

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

        final File folder = new File(Paths.get(System.getProperty("user.home"), "neo4j\\import\\pitc").toUri());
        final String DB_URL = "jdbc:postgresql://192.168.1.60:1105/pitc";
        final String USER = "postgres";
        final String PASS = "";
        Connection connection = getConnection(DB_URL, USER, PASS);
    
        try {
            new WorkFolder(folder).prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        Statement stmt = null;
        if (connection == null) { return; }
        try {
            stmt = connection.createStatement();
            toCSV(stmt, folder.toString(),"select table_name from INFORMATION_SCHEMA.views WHERE table_schema = 'neo'");
            toCSV(stmt, folder.toString(),"select table_name from INFORMATION_SCHEMA.tables WHERE table_schema = 'neo' AND table_type = 'BASE TABLE'");
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    private static void toCSV(Statement stmt, String folder, String query) {
        List<String> models = new ArrayList<>();
        ResultSet res = null;
        try {
            res = stmt.executeQuery(query);
            while (true) {
                    if (!res.next()) break;
                models.add(res.getString("table_name"));
            }
            for(String model:models) {
                List<String> row = new ArrayList<>();
                List<String[]> rows = new ArrayList<>();
                ResultSet resView = stmt.executeQuery(String.format("select * from neo.%s", model));
                int count = resView.getMetaData().getColumnCount();
                for(int i=1; i<=count; i++){
                    row.add(resView.getMetaData().getColumnLabel(i));
                }
                rows.add(row.toArray(String[]::new));
                while (resView.next()) {
                    row.clear();
                    for(int i=1; i<=count; i++){
                        row.add(resView.getString(i));
                    }
                    rows.add(row.toArray(String[]::new));
                }
                try {
                    saveTable(Path.of(folder, model + ".csv") , rows);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    private static void saveTable(Path fn, List<String[]> data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fn.toString()), "UTF8")) { //"cp1251"
            data.stream()
              .map(v -> Arrays.stream(v).collect(Collectors.joining(";")))
              .forEach(v -> {
                  try {
                      out.write(v + "\r\n");
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
