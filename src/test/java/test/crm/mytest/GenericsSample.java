package test.crm.mytest;


import java.util.List;
import java.util.Map;

/**
 * Created by santhosh.b on 16/10/15.
 */
public class GenericsSample {


    public <T> T testMe(Map<String, Object> c, List<T> var2)  {

            Object t = "santhosh";
        return (T)t;
        //return var2.get(0);
    }

    public Object testMe()  {
        return null;
    }
}
