package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream(
                    "config/config.properties"
            );

            properties.load(file);
            file.close();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Could not load config.properties",
                    e
            );
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}