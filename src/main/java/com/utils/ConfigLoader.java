package com.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    //singleton constructor
    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader(System.getProperty("user.dir")+"/src/main/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseURL() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            return prop;
        } else throw new RuntimeException("baseUrl property could not be found in config.properties file");
    }

    public String getUserName() {
        String prop = properties.getProperty("userName");
        if (prop != null) {
            return prop;
        } else
            throw new RuntimeException("user name for trademe login property could not be found in config.properties file");
    }

    public String getPassWord() {
        String prop = properties.getProperty("passWord");
        if (prop != null) {
            return prop;
        } else
            throw new RuntimeException("password for trademe login property could not be found in config.properties file");
    }

    public String getAPIBaseUrl() {
        String prop = properties.getProperty("apiBaseUrl");
        if (prop != null) {
            return prop;
        } else throw new RuntimeException("baseUrl for API property could not be found in config.properties file");
    }


}
