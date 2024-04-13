import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fir extends Thread {
    CyclicBarrier bariera;
    public Fir(CyclicBarrier bariera) {
        this.bariera = bariera;
    }
    public void run() {
        while (true) {
            activitate();
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
    public static void main(String[] args) {
        Lock l1 = new ReentrantLock();
        Lock l2 = new ReentrantLock();
        CyclicBarrier bariera = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("Rutina barierei");
            }
        });
        ExecutionThread F1 = new ExecutionThread(4, 2, 4, 4, 6, l1, l2, bariera);
        ExecutionThread F2 = new ExecutionThread(5, 3, 5, 5, 7, l2, l1, bariera);

        F1.start();
        F2.start();
    }
}