package com.santhosh.framework.internal.config;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;

import java.util.Map;

/**
 * Created by santhosh.b on 28/06/15.
 * This is meant to hold config values required for the framework.
 */
public class FrameworkConfig {


    private static BaseConfiguration baseConfiguration = new BaseConfiguration();
    private static final Logger logger = LoggerFactory.getLogger(FrameworkConfig.class);

    public static void loadDefaultParamValues() {

        for (ConfigName configNameValue : ConfigName.values()) {

            baseConfiguration.addProperty(configNameValue.getConfigName(), configNameValue.getConfigValue());
        }
    }

    public static void loadTestSuiteParamValues(ISuite iSuite) {
        Map suiteParameters = iSuite.getXmlSuite().getAllParameters();
        for (ConfigName configNameValue : ConfigName.values()) {
            if (suiteParameters.containsKey(configNameValue.getConfigName())) {

                baseConfiguration.addProperty(configNameValue.getConfigName(), suiteParameters.get(configNameValue.getConfigName()));


            }

        }
    }

    public static void loadVMParamValues() {

        for (ConfigName configNameValue : ConfigName.values()) {
            String env = System.getProperty(configNameValue.getConfigName());
            if (StringUtils.isNotBlank(env)) {
                logger.info("env:" + env);
                baseConfiguration.setProperty(configNameValue.getConfigName(), env);
            }

        }
    }

    public static String getString(String key) {

        return baseConfiguration.getString(key);

    }

    public static Integer getInteger(String key) {
        return baseConfiguration.getInt(key);
    }

    public static Boolean getBoolean(String key) {
        return baseConfiguration.getBoolean(key);
    }

}


