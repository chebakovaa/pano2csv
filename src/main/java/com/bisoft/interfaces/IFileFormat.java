package com.bisoft.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface IFileFormat {
    void saveStart(File file) throws FileNotFoundException, UnsupportedEncodingException;
    void saveEnd() throws IOException;
}
