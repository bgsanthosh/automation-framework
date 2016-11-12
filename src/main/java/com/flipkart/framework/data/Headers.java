package com.flipkart.framework.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santhosh.b on 30/07/15.
 */
public class Headers {

    private Map<String, String> headerMap = new HashMap<String, String>();

    public void add(String key, String value) {

        headerMap.put(key, value);
    }

    public void add(Map<String,String> map) {

        headerMap.putAll(map);
    }

    public Map<String,String> getHeaderMap()    {

        return headerMap;
    }
    @Override
    public String toString() {

        return headerMap.toString();
    }
}
