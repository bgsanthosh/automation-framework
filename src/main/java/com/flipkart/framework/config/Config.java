package com.flipkart.framework.config;

import com.flipkart.framework.internal.config.ConfigName;
import com.flipkart.framework.internal.config.FrameworkConfig;
import com.google.common.collect.Maps;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.util.Strings;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by santhosh.b on 04/07/15.
 * This is meant to be used by the consumer of the framework.
 */
public class Config {

    private static PropertiesConfiguration config = new PropertiesConfiguration();

    private static boolean isConfigAvailable = false;
    private static final Logger logger = LoggerFactory.getLogger(Config.class);


    public static void init() throws ConfigException {

        String configFileName = FrameworkConfig.getString(ConfigName.CONFIG_FILE_PATH.getConfigName());
        if (!Strings.isNullOrEmpty(configFileName)) {
            try {
                config.load(configFileName);
                isConfigAvailable = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ConfigException("Failed to Parse Client Configuration File");
            }
        } else {

            logger.info("Client Configuration File is Not Configured");
        }

    }

    public static String getString(String key) {
        return config.getString(key);
    }

    public static Integer getInteger(String key) {
        return config.getInt(key);
    }

    public static Boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public static Map<String,Object> getProperties()  {
        Map<String,Object> configMap = Maps.newHashMap();

        if(!isConfigAvailable) return configMap;

        Iterator<String> itrkey = config.getKeys();
        while(itrkey.hasNext()) {
            String key = itrkey.next();
            configMap.put(key,config.getString(key));
        }
        return configMap;
    }

}
