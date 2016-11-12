package test.crm.mytest;

/**
 * Created by santhosh.b on 21/02/16.
 */
public class Access {


    public static void main(String args[])  {

        Object a = new Access();
        Object b = new Bccess();

        System.out.println(a instanceof  Access);
        System.out.println(a instanceof  Bccess);

        System.out.println(b instanceof  Access);
        System.out.println(b instanceof  Bccess);
    }
}

class Bccess  extends Access  {


}
