package com.santhosh.framework.annotation.impl;

import com.santhosh.framework.annotation.Annotation;
import com.santhosh.framework.annotation.Data;
import com.santhosh.framework.internal.config.ConfigName;
import com.santhosh.framework.internal.config.FrameworkConfig;
import com.santhosh.framework.utils.TestNGUtils;
import org.apache.commons.lang.StringUtils;
import org.testng.IInvokedMethod;

import java.io.File;

/**
 * Created by santhosh.b on 28/06/15.
 */
public class DataAnnotation implements Annotation {

    String fileName;
    Class<?> dataProviderClass;
    String className;
    Class<?> testClass;

    private DataAnnotation() {

        //Do Nothing
    }

    public void setFileName(String fileName) {

            this.fileName = fileName;

    }


    public String getFileName() {
        return fileName;
    }


    public Class<?> getDataProviderClass() {
        return dataProviderClass;
    }

    public void setDataProviderClass(Class<?> dataProviderClass) {
        this.dataProviderClass = dataProviderClass;
    }

    public void setTestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public Class getTestClass() {
        return testClass;
    }

    public static DataAnnotation initialize(IInvokedMethod method) {

        DataAnnotation dataAnnotation = new DataAnnotation();
        Data data = (Data) TestNGUtils.getAnnotationForClass(method, Data.class);
        if(!StringUtils.isEmpty(data.fileName())) {
            dataAnnotation.setFileName(data.fileName());
        }
        else    {
            String testSuiteFilePath = FrameworkConfig.getString(ConfigName.TEST_SUITE_FILE_PATH.getConfigName());
            dataAnnotation.setFileName(testSuiteFilePath + File.separator +  TestNGUtils.getTestClassName(method));

        }
        dataAnnotation.setDataProviderClass(data.provider());
        dataAnnotation.setTestClass(method.getTestMethod().getRealClass());
        return dataAnnotation;
    }
}
