package com.bisoft.models;

import com.bisoft.App;
import com.bisoft.exeptions.LoadConnectionParameterException;
import com.bisoft.interfaces.IAppResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AppResource implements IAppResource {
    private final String resourceName;

    public AppResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public Map<String, String> loadedProperties() throws LoadConnectionParameterException {
        Map<String, String> result = new HashMap<>();
        Properties property = new Properties();
        try(InputStream is = App.class.getClassLoader().getResourceAsStream(resourceName)){
            property.load(is);
            property.forEach(
              (k, v) -> result.put((String)k, (String)v)
            );
//                final String DB_URL = property.getProperty("jdbc.url"); // "jdbc:postgresql://192.168.1.60:1105/pitc";
//                final String USER = property.getProperty("jdbc.username"); //"postgres";
//                final String PASS = property.getProperty("jdbc.password");

        } catch (IOException e) {
            e.printStackTrace();
            throw new LoadConnectionParameterException("Loading ConnectionParameter Fail.");
        }
        return result;
    }
}
