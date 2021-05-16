package com.bisoft.interfaces;

import com.bisoft.exeptions.GetTitleObjectException;

import java.io.IOException;

public interface IOpenedFile extends AutoCloseable{
    void save(IModelObject object) throws IOException, GetTitleObjectException;

    public class Fake implements IOpenedFile {

        @Override
        public void save(IModelObject object) throws IOException, GetTitleObjectException {

        }

        @Override
        public void close() throws Exception {

        }
    }

}
