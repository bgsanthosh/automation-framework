import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;

import java.io.*;
import java.util.List;
import java.util.logging.FileHandler;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by santhosh.b on 06/10/15.
 */
public class GetOrders {

    public static void main(String args[]) throws Exception {
        RestAssured.defaultParser = Parser.JSON;
        FileWriter fileWriter = new FileWriter(new File("/Users/santhosh.b/flipkart/orderLine.txt"));
        FileReader fileReader = new FileReader(new File("/Users/santhosh.b/flipkart/accLine.txt"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
try {
    String line = null;
    while ((line = bufferedReader.readLine()) != null) {

        try {

            Response response = null;
            response = given().headers("Accept", "application/json", "Cookie", "_session_id=d2b1d1c3183a3725c25e16879dfa2c38", "X_OMS_CLIENT_ID", "sa").and().get("http://flo-oms-transit-b2c.nm.flipkart.com:80/orders/?bill_to_party_id=" + line);

            JsonPath jsonPath = new JsonPath(response.getBody().asString());
            System.out.println(response.getBody().asString());
            List<String> orderList = jsonPath.get("orders.external_id");
            if (orderList.size() > 1) {
                for (String orderId : orderList) {
                   // System.out.println(orderId);
                    if(!orderId.startsWith("OD")) {
                        bufferedWriter.write(orderId);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }

        }
        catch(Exception e)  {
            e.printStackTrace();}

    }
}
finally {
    bufferedWriter.close();
}

    }


}
