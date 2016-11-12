package test.crm.mytest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by santhosh.b on 21/03/16.
 */
public class DPFibonacci {

    private static Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    public static int fibonacci(int n)  {


        if(n < 2) return 1;
        else if(map.containsKey(n))  {
            System.out.println("Found for:" + n + " = " + map.get(n));
            return map.get(n);
        }
        else    {

            int result = fibonacci(n-1) + fibonacci(n-2);
            map.put(n,result);
            return result;
        }


    }
    public static void main(String args[])  {


        System.out.println(fibonacci(5));
    }
}
