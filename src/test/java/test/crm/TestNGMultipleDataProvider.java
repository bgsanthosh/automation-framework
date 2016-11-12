package test.crm;

import com.santhosh.framework.clients.RESTClient;
import com.santhosh.framework.data.RESTResponse;
import com.santhosh.framework.data.Request;
import com.santhosh.framework.data.TestCase;
import com.santhosh.framework.data.XMLDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by santhosh.b on 15/10/15.
 */
public class TestNGMultipleDataProvider {


    @Test(dataProvider = "xmlDataProvider" , dataProviderClass = XMLDataProvider.class)
    public void multipleDataProviderTest(TestCase testCase) throws Exception   {

        Request request = testCase.getTestStepByName("multipleDataProviderTestStep-1").getRequest();
        RESTResponse response = RESTClient.INSTANCE.execute(request);
        Assert.assertEquals(response.getStatusCode(), 200, "status Code Mismatch");
    }

}

