package com.bisoft.models;

import com.bisoft.exeptions.GetObjectNamesException;
import com.bisoft.exeptions.WriteObjectException;
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
            IModelObject object = collection.next();
            try(IOpenedFile file = target.savedFormat(object.name())){
                file.save(object);
            } catch (Exception e) {
                e.printStackTrace();
                new WriteObjectException("Writing Object Failed");
            }
        }
    }

}
