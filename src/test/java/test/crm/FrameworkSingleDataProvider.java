package test.crm;

import com.santhosh.framework.annotation.Data;
import com.santhosh.framework.clients.RESTClient;
import com.santhosh.framework.data.RESTResponse;
import com.santhosh.framework.data.Request;
import com.santhosh.framework.data.TestRunner;
import com.santhosh.framework.data.XMLDataProvider;
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
