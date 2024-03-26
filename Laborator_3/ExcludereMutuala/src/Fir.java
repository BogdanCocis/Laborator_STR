import java.util.concurrent.locks.Lock;

public class Fir extends Thread {


    Lock l1,l2;

    int sleep_min, sleep_max, activity_min, activity_max,sleepTr3;

    public Fir( int sleep_min, int sleep_max, int activity_min, int activity_max,Lock l1,Lock l2,int sleepTr3) {


        this.sleep_min = sleep_min;

        this.sleep_max = sleep_max;

        this.activity_min = activity_min;

        this.activity_max = activity_max;

        this.l1 = l1;
        this.l2 = l2;
        this.sleepTr3 = sleepTr3;

    }

    public void run() {

        System.out.println(this.getName() + " - STATE 1");
        int k = (int) Math.round(Math.random()*(sleep_max

                - sleep_min) + sleep_min);

        for (int i = 0; i < k * 100000; i++) {

            i++; i--;

        }

        l1.lock();
        System.out.println(this.getName() + " - STATE 2");
        k = (int) Math.round(Math.random()*(activity_max
                - activity_min) + activity_min);

        for (int i = 0; i < k * 100000; i++) {

            i++; i--;

        }
        if(!l2.tryLock()){
            l1.unlock();
            try {
                sleep(sleepTr3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            l1.lock();
            l2.lock();
        }


        System.out.println(this.getName() + " - STATE 3");
        try {
            sleep(sleepTr3*500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l1.unlock();
        l2.unlock();

        System.out.println(this.getName() + " - STATE 4");

    }
}