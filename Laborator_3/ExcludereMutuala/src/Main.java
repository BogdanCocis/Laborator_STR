import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args)
    {
        Lock l1 = new ReentrantLock();
        Lock l2 = new ReentrantLock();
        Fir f1 = new Fir( 2, 4, 4, 6,l1,l2,4);
        f1.start();
        Fir f2 = new Fir( 3, 5, 5, 7,l2,l1,5);
        f2.start();




    }
}