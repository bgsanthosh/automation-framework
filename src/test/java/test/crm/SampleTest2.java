package test.crm;

import com.flipkart.framework.annotation.Data;
import com.flipkart.framework.clients.RESTClient;
import com.flipkart.framework.data.TestRunner;
import com.flipkart.framework.data.XMLDataProvider;
import org.testng.annotations.Test;

/**
 * Created by santhosh.b on 05/07/15.
 */
@Data(provider = XMLDataProvider.class, fileName = "/Users/santhosh.b/flipkart/automation/taf/automation-framework/src/main/resources/PlatformBir.xml")

public class SampleTest2 {

    @Test
    public void PlatformNewDCBir() throws Exception {

        System.out.println("PlatformNewDCBir");
        System.out.println(TestRunner.INSTANCE.getTestCaseList());
        RESTClient.INSTANCE.execute(TestRunner.INSTANCE.getTestCase().getTestStepByName("sampleTest").getRequest());

        System.out.println("Santhosh B Gowda Rocks !!!");
    }

    @Test
    public void PlatformNewDCBir2() throws Exception {

       System.out.println("PlatformNewDCBir2");
        System.out.println(TestRunner.INSTANCE.getTestCaseList());
        RESTClient.INSTANCE.execute(TestRunner.INSTANCE.getTestCase().getTestStepByName("sampleTest").getRequest());

        System.out.println("Santhosh B Gowda Rocks 2!!!");
    }

}
