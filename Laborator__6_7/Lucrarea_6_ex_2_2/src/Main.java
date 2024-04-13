import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class Fir extends Thread {

    CyclicBarrier bariera;
    Semaphore s;
    int permise;
    int activity_min, activity_max;

    public Fir(Semaphore s, int permise, int activity_min, int activity_max, CyclicBarrier bariera) {

        this.bariera = bariera;

        this.activity_min = activity_min;

        this.activity_max = activity_max;
        this.s = s;
        this.permise = permise;
    }

    public void run() {

        while (true) {
            System.out.println(this.getName() + " - STATE 0");
            try {
                this.s.acquire(this.permise);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - STATE 1");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            this.s.release(permise);

            System.out.println(this.getName() + " - STATE 2");

            try {

                bariera.await();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (BrokenBarrierException e) {

                e.printStackTrace();

            }
            activitate();
        }

    }
    public void activitate() {

        System.out.println(this.getName() + "> activitate");

        try {

            Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);

        } catch (InterruptedException e) {

        }

    }

}
class Fir1 extends Thread {
    CyclicBarrier bariera;
    Semaphore s1;
    Semaphore s2;
    int permise1;
    int permise2;
    int activity_min, activity_max;

    public Fir1(Semaphore s1, Semaphore s2, int permise1, int permise2, int activity_min, int activity_max, CyclicBarrier bariera) {

        this.bariera = bariera;

        this.activity_min = activity_min;

        this.activity_max = activity_max;

        this.s1 = s1;
        this.s2 = s2;
        this.permise1 = permise1;
        this.permise2 = permise2;
    }

    public void run() {

        while (true) {
            System.out.println(this.getName() + " - STATE 0");
            try {
                this.s1.acquire(this.permise1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                this.s2.acquire(this.permise2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " - STATE 1");
            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }
            this.s1.release(permise1);
            this.s2.release(permise2);

            System.out.println(this.getName() + " - STATE 2");

            try {

                bariera.await();

            } catch (InterruptedException e) {

                e.printStackTrace();

            } catch (BrokenBarrierException e) {

                e.printStackTrace();

            }

            activitate();

        }

    }

    public void activitate() {

        System.out.println(this.getName() + "> activitate");

        try {

            Thread.sleep(Math.round(Math.random() * 3 + 3) * 1000);

        } catch (InterruptedException e) {

        }

    }

}


public class Main {
    public static void main(String args[]) {

        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);

        CyclicBarrier bariera = new CyclicBarrier(3, new Runnable() {
            public void run() {
                System.out.println("Rutina barierei");
            }
        });
        Fir fir1 = new Fir(s1, 1, 2, 4, bariera);
        Fir1 fir2 = new Fir1(s1, s2, 1, 1, 3, 6, bariera);
        Fir fir3 = new Fir(s2, 1, 2, 5, bariera);
        fir1.start();
        fir2.start();
        fir3.start();
    }
}


