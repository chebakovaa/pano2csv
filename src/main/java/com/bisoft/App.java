package com.bisoft;


import com.bisoft.exeptions.ClearFolderContentException;
import com.bisoft.exeptions.DBConnectionException;
import com.bisoft.exeptions.LoadConnectionParameterException;
import com.bisoft.helpers.StringResource;
import com.bisoft.interfaces.IAppConnection;
import com.bisoft.models.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class App
{
    public static void main(String[] args)
    {
        try {
            Map<String, String> target = new MapResource("target.properties").loadedResource();
            File folder = args[0] != null && args[0].length() > 0 ? new File(args[0])
              : new File(Paths.get(System.getProperty("user.home"), target.get("location")).toUri());

            IAppConnection appConnection = new AppConnection(new MapResource("db.properties").loadedResource());

            new SavedModel(
              new DBSource(
                appConnection.opennedConnection(),
                new StringResource("get_all_table.sql").loadedResource(),
                new StringResource("get_table_content.sql").loadedResource()
              ),
              new FileTarget(
                new FolderContent(folder).clearedFolder(),
                new CSVFormat(target.get("column.delimiter"))
              )
            ).save();
        } catch (ClearFolderContentException | IOException | DBConnectionException | LoadConnectionParameterException e) {
            e.printStackTrace();
        }
    }
    
//    private static void SaveCSV() {
//
//        Properties property = new Properties();
//        InputStream is = App.class.getClassLoader().getResourceAsStream("db.properties");
//        try {
//            property.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        InputStream queryStream = App.class.getClassLoader().getResourceAsStream("get_all_tables.sql");
//        String queryTables = new BufferedReader(
//          new InputStreamReader(queryStream, StandardCharsets.UTF_8))
//            .lines()
//          .collect(Collectors.joining("\n"));
//
//
//
//        try {
//            folderContent.clear();
//            ISavedModel tc = new TableCollection(connection, queryTables);
//            tc.save(folder, new CSVFormat(delimiter));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        catch (ClearFolderContentException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }

}
