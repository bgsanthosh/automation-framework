package com.santhosh.framework.internal.listener;

import com.santhosh.framework.internal.config.ConfigName;
import com.santhosh.framework.internal.config.FrameworkConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * Created by santhosh.b on 13/06/15.
 */

public class SuiteListenerImpl implements ISuiteListener {


    private static final Logger logger = LoggerFactory.getLogger(SuiteListenerImpl.class);


    public SuiteListenerImpl() {


    }

    public void onStart(ISuite iSuite) {

        if (!ListenerController.isListenerDisabled(ConfigName.DISABLE_SUITE_LISTENER)) {

            /*
             * Load the testng.xml suite parameters.
             */
            FrameworkConfig.loadTestSuiteParamValues(iSuite);
        }
    }

    public void onFinish(ISuite iSuite) {

        if (!ListenerController.isListenerDisabled(ConfigName.DISABLE_SUITE_LISTENER)) {
            logger.info("Suite Finished..");
        }
    }
}
