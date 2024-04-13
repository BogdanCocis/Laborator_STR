import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Fir2 extends Thread{
    int nume, sleep, activity_max, activity_min;
    CountDownLatch cdl;
    Semaphore s;

    Fir2(int n, Semaphore sa, int sleep, int activity_min, int activity_max, CountDownLatch cdl) {
        this.nume = n;
        this.s = sa;
        this.sleep = sleep;
        this.activity_min=activity_min;
        this.activity_max=activity_max;
        this.cdl=cdl;
    }

    public void run() {
        try {
            System.out.println("Fir " + nume + " STATE 1");
            s.acquire();
            System.out.println("Fir " + nume + " a luat jetonul");
            Thread.sleep(sleep*1000);
            System.out.println("Fir " + nume + " STATE 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            System.out.println("Fir " + nume + " STATE 3");
            cdl.countDown();
            cdl.await();
            System.out.println("Fir " + nume + " a terminat");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
