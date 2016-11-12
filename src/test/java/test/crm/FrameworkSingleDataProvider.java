package test.crm;

import com.flipkart.framework.annotation.Data;
import com.flipkart.framework.clients.RESTClient;
import com.flipkart.framework.data.RESTResponse;
import com.flipkart.framework.data.Request;
import com.flipkart.framework.data.TestRunner;
import com.flipkart.framework.data.XMLDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by santhosh.b on 14/10/15.
 */
@Data(provider = XMLDataProvider.class)
public class FrameworkSingleDataProvider {


    @Test
    public void singleDataProviderTest() throws Exception   {

        Request request = TestRunner.INSTANCE.getTestCase().getTestStepByName("singleDataProviderTestStep-1").getRequest();
        RESTResponse response = RESTClient.INSTANCE.execute(request);
        Assert.assertEquals(response.getStatusCode(),200,"status Code Mismatch");
    }

}
