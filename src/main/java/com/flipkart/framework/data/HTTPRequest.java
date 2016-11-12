package com.flipkart.framework.data;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class HTTPRequest implements Request {


    private String name;
    private String path;
    private HTTPMethod method;
    private Headers headers;
    private QueryParams queryParams;
    private String body;
    private String endPoint;


    public void setMethod(HTTPMethod method) {

        this.method = method;
    }

    public HTTPMethod getMethod() {

        return method;
    }


    public void setPath(String path) {

        this.path = path;
    }

    public String getPath() {

        return path;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public String getBody() {

        return body;
    }

    public void setHeaders(Headers headers) {

        this.headers = headers;
    }

    public void setQueryParams(QueryParams queryParams) {

        this.queryParams = queryParams;
    }

    public Headers getHeaders() {

        return headers;
    }

    public QueryParams getQueryParams() {

        return queryParams;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Response execute() {
        return null;
    }

    @Override
    public String toString() {

        return "name : " + name + "; path:" + path + "; action:" + method + ";headers:" + headers + ";queryParams:" + queryParams + ";body:" + body;
    }

    public enum HTTPMethod  {

        POST,GET,OPTIONS
    }

}





