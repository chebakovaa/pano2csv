package com.bisoft.interfaces;

import com.bisoft.exeptions.GetObjectNamesException;

import java.io.IOException;
import java.sql.SQLException;

public interface IObjectStructure {
    void save() throws IOException, GetObjectNamesException;
}
