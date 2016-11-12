package com.santhosh.framework.data;

import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class TestSuite {

    private List<TestCase> testCaseList = new ArrayList<TestCase>();
    private String name;
    private String desc;
    private List<Parameters> parametersList = new ArrayList<Parameters>();

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public void addParameters(Parameters parameters) {

        parametersList.add(parameters);
    }

    @Override
    public String toString() {

        return "[ TestSuite { " + parametersList.toString() + "} " + testCaseList.toString() + "]";
    }

    public void addTestCase(TestCase testCase) {

        testCaseList.add(testCase);
    }

    public List<TestCase> getTestCaseByName(String methodName) {

        List<TestCase> returnTestCaseList = new ArrayList<TestCase>();
        if (Strings.isNullOrEmpty(methodName)) return null;

        for (TestCase testCase : testCaseList) {

            if (testCase.getName().equals(methodName)) {
                returnTestCaseList.add(testCase);
            }
        }
        return returnTestCaseList;
    }
}
