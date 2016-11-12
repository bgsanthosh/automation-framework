package test.crm.mytest;

/**
 * Created by santhosh.b on 17/10/15.
 */
public class AllPossiblePalindrome {


    public static void main(String args[])  {


        String str = "geeks";

        for(int i = 0 ; i < str.length() ; i++) {

            for(int j = i; j < str.length(); j++)   {

                String subString = str.substring(i,j);
              //  System.out.println("Parsing:" + subString);
                if(isPalindrome(subString)) System.out.println(subString);
            }
        }
    }

    public static boolean isPalindrome(String str)    {

        String revStr = new StringBuilder(str).reverse().toString();
        //System.out.println("IsPalindrome:" +);
        return revStr.equals(str);
    }
}
