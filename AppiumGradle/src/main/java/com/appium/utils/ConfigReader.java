package com.appium.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    static {
        properties = new Properties();
        try (FileReader reader = new FileReader("src/main/resources/Capabilities.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
