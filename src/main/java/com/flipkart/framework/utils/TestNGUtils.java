package com.flipkart.framework.utils;

import org.testng.IInvokedMethod;

import java.lang.annotation.Annotation;

/**
 * Created by santhosh.b on 14/06/15.
 */
public class TestNGUtils {


    public static boolean isAnnotationPresent(IInvokedMethod method, Class classObject) {

        return method.getTestMethod().getInstance().getClass().getAnnotation(classObject) != null;
    }

    public static Annotation getAnnotationForClass(IInvokedMethod method, Class classObject) {

        return method.getTestMethod().getInstance().getClass().getAnnotation(classObject);
    }

    public static String getTestClassName(IInvokedMethod method)    {

        return method.getTestMethod().getRealClass().getSimpleName();
    }
}
