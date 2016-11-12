package test.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by santhosh.b on 27/03/16.
 */
public class ProdConsume {

    public static void main(String args[])  {

        List<Integer> sharedList = new ArrayList<Integer>();
        String sharedString = new String();
        new Prod(sharedList,sharedString).start();
        new Consume(sharedList,sharedString).start();

    }
}


class Prod extends Thread {

    List<Integer> sharedList;
    String sharedString;

    public Prod(List<Integer> sharedList, String sharedString) {

        this.sharedList = sharedList;
        this.sharedString = sharedString;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (sharedList) {
                if (sharedList.size() != 0) {
                    try {

                        sharedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sharedList.add(new Random().nextInt(1000));
                sharedList.notifyAll();
            }
        }
    }
}

class Consume extends Thread    {

    List<Integer> sharedList;
    String sharedString;

    public Consume(List<Integer> sharedList, String sharedString) {

        this.sharedList = sharedList;
        this.sharedString = sharedString;
    }

    public void run() {

        while (true) {
            synchronized (sharedList) {
                if (sharedList.size() == 0) {
                    try {

                        sharedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(sharedList.remove(0));
                sharedList.notifyAll();
            }
        }
    }
}