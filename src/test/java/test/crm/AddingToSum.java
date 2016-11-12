package test.crm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh.b on 01/04/16.
 */


public class AddingToSum {


    public static void addingToSum(int a[], int start , int end, int sum , List<String> result)    {


        if(start <  0 || start > end || end < start )
            return;

        for(int i = start ; i < end ; i++ ) {

            if(sum - a[i] == 0) {
                result.add(":" + a[i] + ":");
                System.out.println(result);
                result = new ArrayList<String>();
            }
            else if(sum - a[i] > 0 )    {

                sum = sum - a[i];
                result.add(":" + a[i] + ":");
                addingToSum(a,start,i-1,sum,result);
                addingToSum(a,i+1,end,sum,result);
            }
            else if( sum - a[i] < 0 )   {

                continue;
            }
        }

    }

    public static void main(String args[])  {

        int a[] = {1,5,4,2,3};
        List<String> result = new ArrayList<String>();
        addingToSum(a,0,a.length,10,result);

    }
}
