package test.crm.mytest;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by santhosh.b on 17/10/15.
 */
public class Isomorphic {


    public static void main(String args[]) throws Exception {

        char a[] = "abaaaaa".toCharArray();
        char b[] = "xyxxxxx".toCharArray();
        Map<Character,Character> map = Maps.newHashMap();

        if(a.length != b.length)    return;
        for(int i = 0;i < a.length; i++)   {

            if(map.containsKey(a[i]))   {

                char t = map.get(a[i]);
                if(t != b[i]) throw new Exception("Mismatch");
            }
            else    {

                Collection<Character> collection = map.values();
                if(collection.contains(b[i])) throw new Exception("Mismatch");


                map.put(a[i], b[i]);
            }
        }

        System.out.println("Match");
    }
}
