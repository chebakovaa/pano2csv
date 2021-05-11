package com.bisoft.interfaces;

import com.bisoft.exeptions.LoadConnectionParameterException;

import java.io.IOException;
import java.util.Map;

public interface IAppResource {
    Map<String, String> loadedProperties() throws LoadConnectionParameterException;
}
