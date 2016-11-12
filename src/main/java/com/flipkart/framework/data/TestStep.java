package com.flipkart.framework.data;

import com.flipkart.framework.annotation.impl.ContextInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh.b on 14/06/15.
 */
public class TestStep {

    private String name;
    private String desc;
    private List<Parameters> parametersList = new ArrayList<Parameters>();
    private Request request;
    private Response response;

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setDesc(String desc) {

        this.desc = desc;
    }

    public String getDesc() {

        return desc;
    }

    public void addParameters(Parameters parameters) {

        parametersList.add(parameters);
    }

    public List<Parameters> getParametersList()   {

        return parametersList;
    }

    public void populateParamToContext() {

        for(Parameters parameters :parametersList)  {
            if(parameters.getName().equals("context"))  {

                TestRunner.INSTANCE.getTestContext().addAll(parameters.getParameter());
            }

        }
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {

        //TODO: Need to do better than instanceof approach. This is not scalable.
        if(request instanceof RESTRequest) {
         /*
         * Inject context map value into the object
         */
            RESTRequest restRequest = (RESTRequest) request;
            ContextInjector<RESTRequest> contextInjector = new ContextInjector<RESTRequest>(restRequest);

            try {
                restRequest = contextInjector.inject(TestRunner.INSTANCE.getTestContext().get());
            }
            catch(Exception e)  {
                e.printStackTrace();
                return null;
            }

        /*
         * End Injection.
         */

            return restRequest;
        }

        return request;
    }


    @Override
    public String toString() {

        return "[ TestStep { " + parametersList != null ? parametersList.toString() : null + "} {" + request != null ? request.toString() : null + " } ]";
    }


}
