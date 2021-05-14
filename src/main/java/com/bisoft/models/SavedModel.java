package com.bisoft.models;

import com.bisoft.exeptions.GetObjectNamesException;
import com.bisoft.interfaces.*;

import java.io.*;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class SavedModel implements ISavedModel {
    
    private final IModelSource source;
    private final ISaveTarget target;
    
    public SavedModel(IModelSource source, ISaveTarget target) {
        this.source = source;
        this.target = target;
    }
    
    @Override
    public void save() throws IOException, GetObjectNamesException {
        Iterator<IModelObject> collection = source.objectCollection();
        while (collection.hasNext()) {
            try(ISavedFormat format = target.savedFormat()){
                format.save(collection.next());
            };
            
//            String tableName = tables.getString("table_name");
//            String contentQuery = String.format(queryColumns, tableName);
//            ITableContent table = new TableContent(connection, tableName);
//
//
//            format.saveStart((Path.of(folder.toString(), tableName)).toFile());
//            table.save(new DBObjectSource(connection, contentQuery), (ISavedFormat) format);
//            format.saveEnd();
        }
    }

}
