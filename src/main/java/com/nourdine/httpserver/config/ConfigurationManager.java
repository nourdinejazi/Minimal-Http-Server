package com.nourdine.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.nourdine.httpserver.util.Json;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) {
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    };

    @SuppressWarnings("resource")
    public void LoadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HTttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (Exception e) {
            throw new HTttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (Exception e) {
            throw new HTttpConfigurationException("Error parsing configuration file", e);
        }

        try {
            myCurrentConfiguration = Json.FromJson(conf, Configuration.class);
        } catch (Exception e) {
            throw new HTttpConfigurationException("Error parsing configuration file, internal", e);
        }
    };

    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HTttpConfigurationException("No current configuration set");
        }
        return myCurrentConfiguration;
    };

}
