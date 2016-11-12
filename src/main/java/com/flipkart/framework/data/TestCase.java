package com.flipkart.framework.data;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class TestCase {

    private String name;
    private String desc;
    private EnumMap<TestCaseType, Boolean> groups;
    private List<Parameters> parametersList = new ArrayList<Parameters>();
    private List<TestStep> stepList = new ArrayList<TestStep>();
    //private List<Permutation> permutationList = new ArrayList<Permutation>();

    private enum TestCaseType {SANITY, REGRESSION, FUNCTIONAL};

    public TestCase() {

        //this.groups.put(TestCaseType.REGRESSION, false);
        //this.groups.put(TestCaseType.SANITY, false);
        //this.groups.put(TestCaseType.FUNCTIONAL, false);
    }

    public EnumMap<TestCaseType, Boolean> getGroups() {
        return groups;
    }

    public void setGroups(String groupsString) {

        String[] groupList = groupsString.split(",");

        for (String group:groupList) {

            switch(TestCaseType.valueOf(group)) {
                case REGRESSION:
                    groups.put(TestCaseType.REGRESSION, true);
                    break;
                case SANITY:
                    groups.put(TestCaseType.SANITY, true);
                    break;
                case FUNCTIONAL:
                    groups.put(TestCaseType.FUNCTIONAL, true);
                    break;
            }
        }
    }

//    public List<Permutation> getPermutationList() {
//        return permutationList;
//    }
//
//    public void setPermutationList(List<Permutation> permutationList) {
//        this.permutationList = permutationList;
//    }
//
//    public void addPermutation(Permutation permutation) {
//        this.permutationList.add(permutation);
//    }

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

        return "[ TestCase { " + name.toString() + "} " + "{ " + desc.toString() + "}" + stepList +  "]";
    }

    public void addStep(TestStep testStep) {

        stepList.add(testStep);
    }

    public TestStep getTestStepAtIndex(int index)   {

        TestStep testStep = this.stepList.get(index);
        if(testStep == null)    return testStep;
        testStep.populateParamToContext();
        return testStep;
    }

    public TestStep getTestStepByName(String name) throws Exception  {

        for(TestStep step : stepList)   {

            if(name.equals(step.getName())) {
                step.populateParamToContext();
                return step;
            }
        }
        throw new Exception("Step name not found");
    }

    public List<TestStep> getAllTestStep()  {

        return stepList;
    }
}
