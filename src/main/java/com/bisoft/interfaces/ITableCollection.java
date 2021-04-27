package com.bisoft.interfaces;

import com.bisoft.models.CSVFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface ITableCollection {
    void save(File folder, IFileFormat format) throws IOException, SQLException;
}
