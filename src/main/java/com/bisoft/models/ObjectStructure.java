package com.bisoft.models;

import com.bisoft.exeptions.GetObjectNamesException;
import com.bisoft.interfaces.*;

import java.io.*;
import java.util.Iterator;

public class ObjectStructure implements IObjectStructure {
    
    private final IModelSource source;
    private final ISaveTarget target;
    
    public ObjectStructure(IModelSource source, ISaveTarget target) {
        this.source = source;
        this.target = target;
    }
    
    @Override
    public void save() throws IOException, GetObjectNamesException {
        Iterator<IModelObject> collection = source.objectCollection();
        while (collection.hasNext()) {
            try(ISavedFormat format = target.savedFormat(collection.next())){
                format.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ;
            
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
