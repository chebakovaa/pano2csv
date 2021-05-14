package com.bisoft.models;

import com.bisoft.App;
import com.bisoft.exeptions.LoadConnectionParameterException;
import com.bisoft.interfaces.ITypedResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MapResource implements ITypedResource<Map<String, String>> {
    private final String resourceName;

    public MapResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public Map<String, String> loadedResource() throws LoadConnectionParameterException {
        Map<String, String> result = new HashMap<>();
        Properties property = new Properties();
        try(InputStream is = App.class.getClassLoader().getResourceAsStream(resourceName)){
            property.load(is);
            property.forEach(
              (k, v) -> result.put((String)k, (String)v)
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new LoadConnectionParameterException("Loading ConnectionParameter Fail.");
        }
        return result;
    }
}
