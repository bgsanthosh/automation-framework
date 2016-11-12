package com.flipkart.framework.data;

/**
 * Created by santhosh.b on 13/06/15.
 */
public class HTTPResponse implements Response {

    private int statusCode;
    private Headers headers;
    private QueryParams queryParams;
    private String body;

    public HTTPResponse(int statusCode , Headers headers, QueryParams queryParams , String body)    {
        this.statusCode = statusCode;
        this.headers = headers;
        this.queryParams = queryParams;
        this.body = body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public void setStatusCode(int statusCode) {

        this.statusCode = statusCode;
    }


    public int getStatusCode() {

        return statusCode;
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

    public void addToResponseContext(String key , Object value) {

        context.put(key,value);
    }

    public Object getFromResponseContext(String key)  {
        return context.get(key);

    }
}
