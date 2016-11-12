package test.crm;

import com.santhosh.framework.clients.RESTClient;
import com.santhosh.framework.data.RESTResponse;
import com.santhosh.framework.data.TestCase;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.santhosh.framework.data.XMLDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by santhosh.b on 03/11/15.
 */
public class TestOrderToShipEntryPoint {

    @Test(dataProvider = "xmlDataProvider", dataProviderClass = XMLDataProvider.class)
    public void testOrderToShipDTEnable(TestCase testCase)  throws Exception {

        RESTResponse restResponse = RESTClient.INSTANCE.execute(testCase.getTestStepByName("entryPoint").getRequest());
        Response response = restResponse.getRAResponse();
        JsonPath jsonPath = new JsonPath(restResponse.getBody());
        boolean actualValid = jsonPath.get("entry_point_result[0].validation_response[0].valid");
        Assert.assertEquals(actualValid, true, "Valid Flag is incorrect");
    }

    @Test(dataProvider = "xmlDataProvider", dataProviderClass = XMLDataProvider.class)
    public void testOrderToShipDTDisable(TestCase testCase)  throws Exception {

        RESTResponse restResponse = RESTClient.INSTANCE.execute(testCase.getTestStepByName("entryPoint").getRequest());

        Response response = restResponse.getRAResponse();
        JsonPath jsonPath = new JsonPath(restResponse.getBody());
        boolean actualValid = jsonPath.get("entry_point_result[0].validation_response[0].valid");
        Assert.assertEquals(actualValid, false, "Valid Flag is incorrect");
    }
}
