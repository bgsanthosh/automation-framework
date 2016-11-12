package test.crm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santhosh.b on 02/04/16.
 */
public class SplitArray {



    public static void findSplit(int nums[],List<Integer> A , List<Integer> B, int index)   {

        if(index > nums.length - 1) {
            //System.out.println("num length equal");
            //System.out.println(A);
            //System.out.println(B);
            int tempSumA = 0;
            int tempSumB = 0;
            for (int temp1 : A) {

                tempSumA = tempSumA + temp1;

            }
            for (int temp1 : B) {

                tempSumB = tempSumB + temp1;

            }
            if (tempSumA == tempSumB) {

                System.out.println("A:" + A);
                System.out.println("B:" + B);
            }
            return;
        }
            Integer valueAtIndex = nums[index];
            A.add(valueAtIndex);
            findSplit(nums,A,B,index+1);
            A.remove(valueAtIndex);
            B.add(valueAtIndex);
            findSplit(nums,A,B,index+1);
            B.remove(valueAtIndex);





    }


    public static void main(String args[])  {

        int nums[] = {5, 2, 3, 10};
        List<Integer> A = new ArrayList<Integer>();
        List<Integer> B = new ArrayList<Integer>();
        findSplit(nums,A,B,0);

    }





}
