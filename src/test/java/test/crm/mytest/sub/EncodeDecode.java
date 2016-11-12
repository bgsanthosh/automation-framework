package test.crm.mytest.sub;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringReader;

/**
 * Created by sgowda on 03/05/16.
 */
public class EncodeDecode {



    public static void main(String args[]) throws Exception  {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Cars cars = mapper.readValue(new StringReader(carJson),Cars.class);
        System.out.println(cars.brand);
        System.out.println(cars.doors);
    }

}

class Cars {

    @JsonProperty("brand")
    String brand;
    @JsonProperty("doors")
    Integer doors;

}


