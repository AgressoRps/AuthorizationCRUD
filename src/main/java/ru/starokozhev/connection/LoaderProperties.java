package ru.starokozhev.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoaderProperties implements ILoader {
    @Override
    public Properties loadProperties(Properties properties, String name) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(name);
        if (inputStream != null){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
