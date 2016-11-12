package test.crm;

/**
 * Created by santhosh.b on 02/04/16.
 */
public class AllPermutation {


    public static void permute(char a[], int index)    {

        if(index >= a.length)   {
            System.out.println(new String(a));
            return;

        }

        for(int i = index; i < a.length ; i++)   {

            swap(a, i, index);
            permute(a, index + 1);
            swap(a, i, index);


        }
    }

    public static void swap(char a[], int i , int j)    {

        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String arg[])   {

        char a[] = { 'a', 'b', 'c'};
        permute(a,0);

    }
}
