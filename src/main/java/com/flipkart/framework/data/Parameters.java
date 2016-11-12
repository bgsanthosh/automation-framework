package com.flipkart.framework.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santhosh.b on 14/06/15.
 */
public class Parameters {

    private String name;
    private Map<String, Object> parameter = new HashMap<String, Object>();


    public Parameters(String name, Map<String, Object> parameter) {

        this.name = name;
        this.parameter = parameter;

    }

    public Parameters() {

        //Do Nothing
    }

    public Map<String, Object> getParameter() {
        return parameter;
    }

    public void setParameter(Map<String, Object> parameter) {
        this.parameter = parameter;
    }

    public void addParameter(String key, String value) {

        parameter.put(key, value);
    }


    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {

        return name + ": {" + parameter.toString() + "}";
    }

}
