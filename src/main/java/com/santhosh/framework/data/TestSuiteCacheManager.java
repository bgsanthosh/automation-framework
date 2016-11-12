package com.santhosh.framework.data;

import com.santhosh.framework.annotation.impl.DataAnnotation;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Created by santhosh.b on 28/06/15.
 */
/*
 * Objective of this class is to manage the list of parsed testsuite data
 * and cache the same.
 */
public class TestSuiteCacheManager {
    /*
     * TODO: This map can grow if there are too many TestClass to be executed.
     * Need to find a way through which this can be cleaned up.
     * AfterInvocation can be the place ?
     */
    private static Map<Class, TestSuite> testSuiteCache = new ConcurrentHashMap<Class, TestSuite>();
    private static final Logger logger = LoggerFactory.getLogger(TestSuiteCacheManager.class);
    private static Cache<Class, TestSuite> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build();

    /*
     * This is used by custom Annotation @Data
     */
    public static List<TestCase> getTestCase(final DataAnnotation dataAnnotation, IInvokedMethod invokedMethod) throws ExecutionException {

        Class testClass = dataAnnotation.getTestClass();
        final Class dataProviderClass = dataAnnotation.getDataProviderClass();
        String methodName = invokedMethod.getTestMethod().getMethodName();
        TestSuite testSuite = null;


        try {

            testSuite = cache.get(testClass, new Callable<TestSuite>() {

                public TestSuite call() {

                    try {
                        logger.info("No Data Found in Case -- Parsing the data file:" + dataAnnotation.getFileName());
                        final Object object = dataProviderClass.newInstance();
                        final Method method = dataProviderClass.getDeclaredMethod("parse", String.class);
                        return (TestSuite) method.invoke(object, dataAnnotation.getFileName());

                    } catch ( Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return testSuite.getTestCaseByName(methodName);
    }

    /*
     * This is used by @DataProvider of TestNG
     */
    public static List<TestCase> getTestCase(Class cacheKey, final Class dataProviderClass, final String fileName , Method method) throws ExecutionException {

        String methodName = method.getName();
        TestSuite testSuite = null;


        try {

            testSuite = cache.get(cacheKey, new Callable<TestSuite>() {

                public TestSuite call() {

                    try {
                        logger.info("No Data Found in Case -- Parsing the data file:" + fileName);
                        final Object object = dataProviderClass.newInstance();
                        final Method method = dataProviderClass.getDeclaredMethod("parse", String.class);
                        return (TestSuite) method.invoke(object, fileName);

                    } catch ( Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return testSuite.getTestCaseByName(methodName);
    }

}