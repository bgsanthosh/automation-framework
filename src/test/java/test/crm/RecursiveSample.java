package test.crm;

/**
 * Created by santhosh.b on 02/04/16.
 */
public class RecursiveSample {


    public static boolean array220(int[] nums, int index, int prev) {

        if(index >= nums.length) return false;

        if(nums[index] == 10 * prev) return true;

        return array220(nums,index+1, nums[index]);



    }

    public static void main(String args[])  {

        int nums[] = {1, 2, 21,4,40};
        System.out.println(array220(nums,0,0));


    }
}
