import java.util.concurrent.Semaphore;

public class Fir extends Thread {
    Semaphore s;
    int activity_min, activity_max, permise, sleepTime;

    public Fir(int activity_min, int activity_max, Semaphore s, int permise, int sleepTime) {
        this.activity_min = activity_min;
        this.activity_max = activity_max;
        this.s = s;
        this.permise = permise;
        this.sleepTime = sleepTime;
    }

    public void run() {
        while (true) {
            System.out.println(this.getName() + " - STATE 0");
            System.out.println(this.getName() + " - STATE 1");

            try {
                this.s.acquire(this.permise);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.getName() + " - STATE 2");


            int k = (int) Math.round(Math.random() * (activity_max - activity_min) + activity_min);
            for (int i = 0; i < k * 100000; i++) {
                i++;
                i--;
            }

            this.s.release(permise);

            System.out.println(this.getName() + " - STATE 3");

            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
