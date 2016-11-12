package com.santhosh.framework.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santhosh.b on 30/07/15.
 */
public class QueryParams {

    private Map<String, String> queryMap = new HashMap<String, String>();

    public void add(String key, String value) {

        queryMap.put(key, value);
    }

    public void add(Map<String, String> map) {

        queryMap.putAll(map);
    }

    public Map<String,String> getQueryMap()    {

        return queryMap;
    }

    @Override
    public String toString() {

        return queryMap.toString();
    }
}
