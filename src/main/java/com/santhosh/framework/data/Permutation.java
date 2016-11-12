package com.santhosh.framework.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sindhuri.kuppasad on 04/09/15.
 */
public class Permutation {
    private String name;
    private String desc;
    private List<Parameters> parametersList = new ArrayList<Parameters>();
    private List<TestStep> stepList = new ArrayList<TestStep>();

    public void addParameters(Parameters parameters) {
        parametersList.add(parameters);
    }

    public void addTestStep(TestStep testStep) {
        stepList.add(testStep);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<Parameters> getParametersList() {
        return parametersList;
    }

    public List<TestStep> getStepList() {
        return stepList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setParametersList(List<Parameters> parametersList) {
        this.parametersList = parametersList;
    }

    public void setStepList(List<TestStep> stepList) {
        this.stepList = stepList;
    }

    @Override
    public String toString() {
        return "[ TestPermutation { " + parametersList.toString() + "} " + stepList.toString() + "]";
    }
}
