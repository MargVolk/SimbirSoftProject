package ru.margarite.volkova.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;
    private Properties properties;

    private ConfigProvider() {
        loadProperties();
    }

    private void loadProperties() {
        String configPath = Paths.get("src/test/resources/app.properties").toAbsolutePath().toString();

        try (FileInputStream file = new FileInputStream(configPath)) {
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Во время чтения настроек произошла ошибка " + e);
        }
    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            instance = new ConfigProvider();
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
