import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public class ExecutionThread extends Thread {
    Integer monitor1, monitor2;
    int sleep_min1, sleep_max1, activity_min1, activity_max1, activity_min2, activity_max2;
    Lock l1, l2;

    CyclicBarrier bariera;

    public ExecutionThread(Integer monitor1, Integer monitor2, int sleep_min1, int sleep_max1, int activity_min1, Lock l1, Lock l2, CyclicBarrier bariera) {
        this.monitor1 = monitor1;
        this.monitor2 = monitor2;
        this.sleep_min1 = sleep_min1;
        this.sleep_max1 = sleep_max1;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;
        this.activity_max2 = activity_max2;
        this.activity_min1 = activity_min2;
        this.l1 = l1;
        this.l2 = l2;
        this.bariera = bariera;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 1");
            int k = (int) Math.round(Math.random() * (activity_max1 - activity_min1) + activity_min1);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            l1.lock();
            //l2.lock();
            System.out.println(this.getName() + " - STATE 2");
            int m = (int) Math.round(Math.random() * (activity_max2 - activity_min2) + activity_min2);
            for (int i = 0; i < m * 100000; i++) {
                i++;
                i--;
            }

            if (l2.tryLock()) {
                System.out.println(this.getName() + " - STATE 3");
                try {
                    Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                l1.unlock();
                try {
                    Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                l1.lock();
                l2.lock();
                System.out.println(this.getName() + " - STATE 3");
                try {
                    Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            l1.unlock();
            l2.unlock();

            System.out.println(this.getName() + " - STATE 4");

            try {
                bariera.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
