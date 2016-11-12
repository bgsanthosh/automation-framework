package com.santhosh.framework.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by santhosh.b on 30/08/15.
 *
 * This maintains the context of the current executing
 * testcase.
 */
public enum TestRunner {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);

    private static ThreadLocal<List<TestCase>> testCaseThreadLocal = new ThreadLocal<List<TestCase>>();

    private static ThreadLocal<TestContext> testContextThreadLocal = new ThreadLocal<TestContext>();

    private static ThreadLocal<List<TestCase>> getTestCaseThreadLocal() {
        return testCaseThreadLocal;
    }

    public static TestCase getTestCase() {

        return getTestCaseThreadLocal().get().get(0);
    }

    public static List<TestCase> getTestCaseList() {

        return getTestCaseThreadLocal().get();
    }


    public void setTestCase(List<TestCase> testCaseList) {
        getTestCaseThreadLocal().set(testCaseList);
    }

    private ThreadLocal<TestContext> getTestContextThreadLocal() {
        return testContextThreadLocal;
    }

    public void setTestCaseContext(TestContext testContext) {

        getTestContextThreadLocal().set(testContext);
    }

    public TestContext getTestContext()    {

        return getTestContextThreadLocal().get();
    }

    public void updateTestContext(String key, Object value) {

        TestContext testContext = getTestContextThreadLocal().get();
        testContext.add(key,value);
    }



}



