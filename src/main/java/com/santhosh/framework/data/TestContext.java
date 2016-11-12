package com.santhosh.framework.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by santhosh.b on 28/06/15.
 */
public class TestContext {

    Map<String,Object> context = new HashMap<String,Object>();

    public TestContext(Map<String,Object> context)  {
        this.context = context;
    }

    public void addAll(Map<String,Object> context)  {

        this.context.putAll(context);
    }

    public Map<String,Object> get() {
        return context;
    }

    public void add(String key, Object value)   {
        this.context.put(key,value);
    }


}
