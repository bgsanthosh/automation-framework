package com.flipkart.framework.internal.config;

public enum ConfigName {


    DISABLE_LISTENER("disable.listener", "false"),

    DISABLE_INVOKED_METHOD_LISTENER("disable.invoked.method.listener", "false"),

    DISABLE_SUITE_LISTENER("disable.suite.listener", "false"),

    CONFIG_FILE_PATH("config.file.path", ""),

    REQUEST_CONFIG_FILE_PATH("request.config.file.path", ""),

    TEST_SUITE_FILE_PATH("testsuite.file.path", "");

    private String configName;
    private String configValue;

    ConfigName(String configName, String configValue) {

        this.configName = configName;
        this.configValue = configValue;

    }

    public String getConfigName() {
        return configName;
    }

    public String getConfigValue() {
        return configValue;
    }


    public enum Scope {

        SYSTEM, TESTSUITE, TESTCLASS, TESTCASE, TESTSTEP

    }
}
