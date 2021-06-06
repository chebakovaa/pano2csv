package com.bisoft.interfaces;

import com.bisoft.exeptions.GetObjectNamesException;

import java.io.IOException;


public interface IObjectStructure {
    void save() throws IOException, GetObjectNamesException;
}
