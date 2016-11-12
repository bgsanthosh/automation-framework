package com.santhosh.framework.data;

import com.jayway.restassured.response.*;
import com.jayway.restassured.response.Response;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class RESTResponse extends HTTPResponse {

    public RESTResponse(int statusCode , Headers headers, QueryParams queryParams , String body)    {
        super(statusCode,headers,queryParams,body);
    }

    public com.jayway.restassured.response.Response getRAResponse() {

        return (Response) getFromResponseContext("restAssuredObject");
    }

}
