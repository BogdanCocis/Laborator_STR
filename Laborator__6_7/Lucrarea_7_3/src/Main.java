import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(3);
        Semaphore s1 = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);
        Fir1 f1;
        Fir2 f2, f3;
        f1 = new Fir1(1, s1, s2, 7, 2, 3, cdl);
        f2 = new Fir2(2, s1,3, 3, 5, cdl);
        f3 = new Fir2(3, s2,4, 4, 6, cdl);
        f1.start();
        f2.start();
        f3.start();
    }
}