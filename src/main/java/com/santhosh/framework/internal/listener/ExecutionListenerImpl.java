package com.santhosh.framework.internal.listener;

import com.santhosh.framework.config.Config;
import com.santhosh.framework.config.ConfigException;
import com.santhosh.framework.data.ConfigRequestParser;
import com.santhosh.framework.internal.config.FrameworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IExecutionListener;

/**
 * Created by santhosh.b on 04/07/15.
 */
public class ExecutionListenerImpl implements IExecutionListener {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionListenerImpl.class);

    public void onExecutionStart() {

        /*
         * Loading the default param values
         */
        FrameworkConfig.loadDefaultParamValues();
        /*
         * Loading the VM parameters
         */
        FrameworkConfig.loadVMParamValues();

        /*
         * Load Client Configuration file
         */
        try {

            Config.init();
        } catch (ConfigException configException) {

            logger.info(configException.getMessage() + " : Unable to Parse Client Configuration");
        }

        /*
         * Load Client Request Configuration File
         */
        try {

            ConfigRequestParser configRequestParser = ConfigRequestParser.createInstance();
            configRequestParser.init();

        } catch (ConfigException configException) {

            logger.info(configException.getMessage() + " : Unable to Parse Client Request Configuration");
        }

    }

    public void onExecutionFinish() {

    }
}
