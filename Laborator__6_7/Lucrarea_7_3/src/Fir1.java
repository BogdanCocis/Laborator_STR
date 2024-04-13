import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Fir1 extends Thread {
    int nume, sleep, activity_max, activity_min;
    CountDownLatch cdl;
    Semaphore s1, s2;

    Fir1(int n, Semaphore s1, Semaphore s2, int sleep, int activity_min, int activity_max, CountDownLatch cdl) {
        this.nume = n;
        this.s1 = s1;
        this.s2=s2;
        this.sleep = sleep;
        this.activity_min=activity_min;
        this.activity_max=activity_max;
        this.cdl=cdl;
    }

    public void run() {
        try {
            System.out.println("Fir " + nume + " STATE 1");
            Thread.sleep(sleep*1000);
            System.out.println("Fir " + nume + " STATE 2");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            this.s1.release();
            this.s2.release();
            System.out.println("Fir " + nume + "jeton in semafoare");
            System.out.println("Fir " + nume + " STATE 3");
            cdl.countDown();
            cdl.await();
            System.out.println("Fir " + nume + " a terminat executia");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
