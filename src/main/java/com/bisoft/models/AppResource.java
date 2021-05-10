package com.bisoft.models;

import com.bisoft.App;
import com.bisoft.interfaces.IAppResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppResource implements IAppResource {
    private final String resourceName;

    public AppResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public IAppResource loadedProperties() throws IOException {
        Properties property = new Properties();
        try(InputStream is = App.class.getClassLoader().getResourceAsStream(resourceName)){
            try {
                property.load(is);
                final String DB_URL = property.getProperty("jdbc.url"); // "jdbc:postgresql://192.168.1.60:1105/pitc";
                final String USER = property.getProperty("jdbc.username"); //"postgres";
                final String PASS = property.getProperty("jdbc.password");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
