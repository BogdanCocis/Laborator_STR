import java.util.concurrent.locks.Lock;

public class ExecutionThread extends Thread {
    int sleep_min1, sleep_max1, activity_min1, activity_max1, activity_min2, activity_max2;
    Lock l1, l2;

    public ExecutionThread(int sleep_min1, int sleep_max1, int activity_min1, int activity_max1, int activity_min2, int activity_max2, Lock l1, Lock l2) {
        this.sleep_min1 = sleep_min1;
        this.sleep_max1 = sleep_max1;
        this.activity_min1 = activity_min1;
        this.activity_max1 = activity_max1;
        this.activity_max2 = activity_max2;
        this.activity_min1 = activity_min2;
        this.l1 = l1;
        this.l2 = l2;
    }

    public void run() {
        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random() * (activity_max1 - activity_min1) + activity_min1);
        for (int i = 0; i < k * 100000; i++) {
            i++;
            i--;
        }
        l1.lock();
        System.out.println("Am luat primul lock");
        System.out.println(this.getName() + " - STATE 2");
        int m = (int) Math.round(Math.random() * (activity_max2 - activity_min2) + activity_min2);
        for (int i = 0; i < m * 100000; i++) {
            i++;
            i--;
        }


        if (l2.tryLock()) {
            System.out.println("Am luat al doilea lock");
            System.out.println(this.getName() + " - STATE 3");
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            l1.unlock();
            System.out.println("Am eliberat primul lock");
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l1.lock();
            l2.lock();
            System.out.println("Am luat ambele lock uri");
            System.out.println(this.getName() + " - STATE 3");
            try {
                Thread.sleep(Math.round(Math.random() * (sleep_max1 - sleep_min1) + sleep_min1) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        l1.unlock();
        l2.unlock();
        System.out.println("Am eliberat ambele lock uri");
        System.out.println(this.getName() + " - STATE 4");
    }

}
