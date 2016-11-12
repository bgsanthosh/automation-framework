package com.santhosh.framework.data;

import com.santhosh.framework.annotation.Context;

/**
 * Created by santhosh.b on 13/06/15.
 */
@Context
public class RESTRequest extends HTTPRequest {


    public Response execute() {
        return null;
    }


    @Override
    public String toString() {

        return super.toString();
    }

    public RESTRequest()    {
        //Do Nothing
    }
    public RESTRequest(RESTRequest request) {
        this.setBody(new String(request.getBody()));
        this.setMethod(request.getMethod());
        this.setEndPoint(new String(request.getEndPoint()));
        this.setName(new String(request.getName()));
        this.setPath(new String(request.getPath()));
        Headers headers = new Headers();
        if(request.getHeaders() != null && request.getHeaders().getHeaderMap() != null)
         headers.add(request.getHeaders().getHeaderMap());
        this.setHeaders(headers);
        QueryParams queryParams = new QueryParams();
        if(request.getQueryParams() != null && request.getQueryParams().getQueryMap() != null)
         queryParams.add(request.getQueryParams().getQueryMap());
        this.setQueryParams(queryParams);

    }
}
