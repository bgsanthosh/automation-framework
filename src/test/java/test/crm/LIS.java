package test.crm;

/**
 * Created by santhosh.b on 29/03/16.
 */
public class LIS {


    public static int testDP(int a[],int length,int max_count) {


        System.out.println();
        int count = 0;
        for(int i = 0 ; i < length ; i++) {

            System.out.print(a[i] + ":" + length + ":" + max_count);
            if(a[i] < a[length-1]) count++;
            if(count > max_count) max_count = count;
            testDP(a,i,max_count);

        }

        return max_count;
    }

    public static void main(String args[])  {

        int a[] = { 1, 2, 7, 4, 5 ,3, 8 };
        System.out.println(a.length);
        System.out.println(testDP(a,a.length,1));

    }
}
