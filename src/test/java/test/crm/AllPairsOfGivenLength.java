package test.crm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh.b on 03/04/16.
 */
public class AllPairsOfGivenLength {


    public static void allPairs(char num[], int index, List<Character> result ) {
        if(index >= num.length) {

            System.out.println(result);
            result = new ArrayList<Character>();
            return;
        }

        result.add(Character.valueOf(num[index]));
        allPairs(num,index+1,result);
        result.remove(Character.valueOf(num[index]));
        allPairs(num,index+1,result);



    }
    public static void main(String args[])  {


        char num[] = {'a','b','c','d'};
        allPairs(num,0,new ArrayList<Character>());

    }
}
