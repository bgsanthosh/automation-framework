package com.santhosh.framework.internal.listener;

import com.santhosh.framework.annotation.Data;
import com.santhosh.framework.annotation.impl.DataAnnotation;
import com.santhosh.framework.config.Config;
import com.santhosh.framework.data.TestCase;
import com.santhosh.framework.data.TestContext;
import com.santhosh.framework.data.TestRunner;
import com.santhosh.framework.data.TestSuiteCacheManager;
import com.santhosh.framework.internal.config.ConfigName;
import com.santhosh.framework.utils.TestNGUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class InvokedMethodListenerImpl implements IInvokedMethodListener {

    private static final Logger logger = LoggerFactory.getLogger(InvokedMethodListenerImpl.class);

    public InvokedMethodListenerImpl() {

    }


    public void beforeInvocation(IInvokedMethod method, ITestResult iTestResult, ITestContext iTestContext) {


    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult, ITestContext iTestContext) {

    }

    public void beforeInvocation(IInvokedMethod method, ITestResult iTestResult) {

        if (ListenerController.isListenerDisabled(ConfigName.DISABLE_INVOKED_METHOD_LISTENER)) {
            return;
        }

        if (!method.isTestMethod()) {
            return;
        }

        boolean isDataAnnotationClass = TestNGUtils.isAnnotationPresent(method, Data.class);

        if (!isDataAnnotationClass) {
            /*
             * Set the context with Config value
             */
            TestRunner.INSTANCE.setTestCaseContext(new TestContext(Config.getProperties()));
            return;
        }

        DataAnnotation dataAnnotation = DataAnnotation.initialize(method);

        logger.info("Data annotation for test class :" + dataAnnotation.getTestClass().getName());
        logger.info("Data provider for the annotation :" + dataAnnotation.getDataProviderClass());
        logger.info("Test suite file :" + dataAnnotation.getFileName());

        try {
            List<TestCase> currentTestCaseList = TestSuiteCacheManager.getTestCase(dataAnnotation, method);
            logger.info("Setting the testCaseList in context:" + currentTestCaseList);
            /*
             * Set the current executing testcase in the testRunner
             */
            TestRunner.INSTANCE.setTestCase(currentTestCaseList);
            /*
             * Set the context with Config value
             */
            TestRunner.INSTANCE.setTestCaseContext(new TestContext(Config.getProperties()));

        } catch (ExecutionException e) {
            e.printStackTrace();
            //Do nothing
        }


    }

    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

    }
}
