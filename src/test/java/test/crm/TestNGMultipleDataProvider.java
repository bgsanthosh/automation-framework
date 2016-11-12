package test.crm;

import com.flipkart.framework.clients.RESTClient;
import com.flipkart.framework.data.RESTResponse;
import com.flipkart.framework.data.Request;
import com.flipkart.framework.data.TestCase;
import com.flipkart.framework.data.TestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by santhosh.b on 15/10/15.
 */
public class TestNGMultipleDataProvider {


    @Test(dataProvider = "xmlDataProvider" , dataProviderClass = com.flipkart.framework.data.XMLDataProvider.class)
    public void multipleDataProviderTest(TestCase testCase) throws Exception   {

        Request request = testCase.getTestStepByName("multipleDataProviderTestStep-1").getRequest();
        RESTResponse response = RESTClient.INSTANCE.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200, "status Code Mismatch");
    }

}

