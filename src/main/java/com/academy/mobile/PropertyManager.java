package com.academy.mobile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager instance;
    private static final String DEFAULT_PROP_PATH = "src/main/resources/cfg.properties";
    private static final String SYSTEM_PROP_ARG = "cfg";
    private Properties properties;

    private PropertyManager() {
        loadProperties();
    }

    public static PropertyManager getInstance() {
        if (instance == null)
            instance = new PropertyManager();

        return instance;
    }

    public Properties getProperties() {
        return properties;
    }

    private void loadProperties() {
        this.properties = new Properties();
        try {
            this.properties.load(new FileReader(new File(System.getProperty(SYSTEM_PROP_ARG, DEFAULT_PROP_PATH))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
