package test.crm;

import com.flipkart.framework.annotation.Data;
import com.flipkart.framework.data.TestCase;
import com.flipkart.framework.data.TestRunner;
import com.flipkart.framework.data.XMLDataProvider;
import org.testng.annotations.Test;


/**
 * Created by santhosh.b on 13/06/15.
 */

/*
 * Idea being once the class is annotated with @Data provider all the
 * @Test method will have an exposure to testRunner object which stores the
 * the context details required for the test case execution.
 * for e.x
 *  RESTResponse restResponse = new RESTClientImpl.execute(testRunner.testCase.testStepByName("testStepName").restRequest)
 *  assert restResponse.getBody() == "somebody";
 *  assert restResponse.headers.getHeaderByName("someHeader") == "santhoshgowda";
 *
 *  If ConfigRESTRequest is used -- The required data will be fetched from an existing store[template folder]
 *  and values for the same will be overridden with the parameters
 *
 * Other thought about support
 * 1]Runtime Evaluation for certain fields via Groovy Script Execution
 * 2]Each test method will be reflected with testCase context with required test Data....
 * 3]Not to compromise on the Parallel execution support of TestNG.
 */

//@Data(provider = XMLDataProvider.class, fileName = "/Users/santhosh.b/flipkart/automation/core-framework/framework/src/main/resources/sample.xml")
public class SampleTest {


    @Test(dataProvider="xmlDataProvider", dataProviderClass=XMLDataProvider.class)
    public void PlatformNewDCBir(TestCase testCase) {

        System.out.println("Test Me");
        System.out.println(testCase);
    }

    @Test(dataProvider="xmlDataProvider", dataProviderClass=XMLDataProvider.class)
    public void PlatformNewDCBir2(TestCase testCase) {

        System.out.println("Test Me 2");
        System.out.println(testCase);

    }
}
