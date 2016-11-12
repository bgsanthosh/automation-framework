package test.crm;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.*;


/**
 * Created by santhosh.b on 03/09/15.
 */
public class BIR {


    @Test
    public void doBir() throws Exception {

        RestAssured.defaultParser = Parser.JSON;
        Response response = null;

        response = given().get("http://iaas-infra-0001.nm.flipkart.com:4100/appdata?path=%2Fapps%2Fcs-aggregator%2Finstances&version=beta");

        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        List<String> ipList = jsonPath.get("d.primary_ip");
        System.out.println(ipList);
        for(String s : ipList)  {
            String url = "http://" + s + ":27661/tasks/rotation_status?state=bir";
            System.out.println(url);
            response = given().post(url);
            response.prettyPrint();

        }
    }

}
