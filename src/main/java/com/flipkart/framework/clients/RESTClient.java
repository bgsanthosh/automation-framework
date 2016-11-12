package com.flipkart.framework.clients;

import com.flipkart.framework.annotation.impl.ContextInjector;
import com.flipkart.framework.data.*;
import com.flipkart.framework.data.Headers;
import com.flipkart.framework.data.Response;
import com.jayway.restassured.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;

/**
 * Created by santhosh.b on 30/07/15.
 */
public enum RESTClient {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(RESTClient.class);


    public com.flipkart.framework.data.RESTResponse execute(Request request)  {

        if(!(request instanceof RESTRequest)) return  null;

        RESTRequest restRequest = (RESTRequest) request;

        String path = restRequest.getPath();
        HTTPRequest.HTTPMethod method = restRequest.getMethod();
        String endPoint = restRequest.getEndPoint();
        com.jayway.restassured.response.Response response = null;
        List<Header> headerList = new ArrayList<Header>();
        com.jayway.restassured.response.Headers restHeaders = null;
        if(restRequest.getHeaders() != null)    {

            Headers headers = restRequest.getHeaders();

            for(String key : headers.getHeaderMap().keySet())   {
                String value = headers.getHeaderMap().get(key);
                Header header = new Header(key,value);
                headerList.add(header);

            }
            restHeaders = new com.jayway.restassured.response.Headers(headerList);
        }
        logger.info("Request EndPoint:" + restRequest.getEndPoint());
        logger.info("Request Headers:" + restHeaders);
        logger.info("Request Body:" + restRequest.getBody());

        switch(method)  {

            case POST:
                response = given().headers(restHeaders).body(restRequest.getBody()).post(endPoint + path );
                break;
            case GET:
                 response = given().headers(restHeaders).get(endPoint + path);
                break;
            case OPTIONS:

            //default: throw new Exception("Invalid Method");

        }


        logger.info("Body:" + response.getBody().asString());
        Headers headers = new Headers();
        for(Header header : response.getHeaders().asList()) {
            headers.add(header.getName(),header.getValue());
        }

        logger.info("Status Code: " + response.getStatusCode());
        logger.info("headers: " + headers);
        logger.info("Status Line: " + response.getStatusLine());


        RESTResponse restResponse = new RESTResponse(response.getStatusCode(),headers,null,response.getBody().asString());
        restResponse.addToResponseContext("restAssuredObject", response);
        return restResponse;
    }




}
