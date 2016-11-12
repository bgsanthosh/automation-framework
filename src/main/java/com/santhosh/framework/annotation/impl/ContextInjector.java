package com.santhosh.framework.annotation.impl;

import com.santhosh.framework.data.HTTPRequest;
import com.santhosh.framework.data.Headers;
import com.santhosh.framework.data.QueryParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by santhosh.b on 02/09/15.
 */
public class ContextInjector<T> {

    T t;
    private static final Logger logger = LoggerFactory.getLogger(ContextInjector.class);

    public ContextInjector(T t) {

        this.t = t;
    }

    public T inject(Map<String, Object> contextMap) throws Exception {

        for (Method getMethod : t.getClass().getMethods()) {

            Method setMethod;

            if (!getMethod.getName().contains("get")) {

                continue;
            }

            logger.info("Found Method Name : " + getMethod.getName());
            logger.info("Return Type:" + getMethod.getReturnType().getSimpleName());


            switch (SupportedInjectorClassEnum.valueOf(getMethod.getReturnType().getSimpleName())) {

                case String:
                    String getString = (String) getMethod.invoke(t);
                    try {

                        setMethod = t.getClass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), String.class);
                    } catch (NoSuchMethodException e) {
                        //Lets try in super class
                        setMethod = t.getClass().getSuperclass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), String.class);
                    }
                    StrSubstitutor sub = new StrSubstitutor(contextMap);
                    String resolvedString = sub.replace(getString);
                    setMethod.invoke(t, resolvedString);
                    break;
                case Map:
                    //TODO:
                    break;
                case List:
                    //TODO:
                    break;
                case Headers:
                    Headers headers = (Headers) getMethod.invoke(t);
                    if (headers == null) continue;
                    Map<String, String> headersMap = headers.getHeaderMap();

                    for(String key :headersMap.keySet())    {
                        sub = new StrSubstitutor(contextMap);
                        resolvedString = sub.replace(headersMap.get(key));
                        headersMap.put(key,resolvedString);
                    }
                    try {

                        setMethod = t.getClass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), Headers.class);
                    } catch (NoSuchMethodException e) {
                        //Lets try in super class
                        setMethod = t.getClass().getSuperclass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), Headers.class);
                    }

                    setMethod.invoke(t, headers);
                    break;

                case QueryParams:

                    QueryParams queryParams = (QueryParams) getMethod.invoke(t);
                    if (queryParams == null) continue;
                    Map<String, String> queryMap = queryParams.getQueryMap();

                    for(String key :queryMap.keySet())    {
                        sub = new StrSubstitutor(contextMap);
                        resolvedString = sub.replace(queryMap.get(key));
                        queryMap.put(key,resolvedString);
                    }
                    try {

                        setMethod = t.getClass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), QueryParams.class);
                    } catch (NoSuchMethodException e) {
                        //Lets try in super class
                        setMethod = t.getClass().getSuperclass().getDeclaredMethod("set" + StringUtils.substring(getMethod.getName(), 3), QueryParams.class);
                    }

                    setMethod.invoke(t, queryParams);
                    break;

                case HTTPMethod:
                    break;
                case Class:
                    //TODO: Context value for this is not supported for now
                    break;

            }

        }

        return t;
    }
}

enum SupportedInjectorClassEnum {

    List(List.class), Map(Map.class), Headers(Headers.class), QueryParams(QueryParams.class), String(String.class), HTTPMethod(HTTPRequest.HTTPMethod.class),Class(Class.class);

    private String name;

    SupportedInjectorClassEnum(Class<?> cls) {
        this.name = cls.getName();
    }

    public String getName() {

        return name;
    }
}