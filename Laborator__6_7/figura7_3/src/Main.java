import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

class Fir extends Thread {
    List<Integer> list = new ArrayList<Integer>();
    int sleepTime;
    Exchanger<List<Integer>> exchanger;
    String nume;

    Fir(int sT, Exchanger<List<Integer>> exchanger, String nume) {
        this.sleepTime = sT;
        this.exchanger = exchanger;
        this.nume = nume;
    }

    public void afisLista() {
        System.out.println(nume + ": " + list);
    }

    public void run() {
        int noElem = (int) Math.round(Math.random() * 3 + 1);
        for (int i = 0; i < noElem; i++) { // populate list with a random number of elements
            int elem = (int) Math.round(Math.random() * 100);
            list.add(elem);
        }
        this.afisLista(); // display list before exchange
        try {
            Thread.sleep(this.sleepTime); // thread waits for x ms
            // exchanging objects
            this.list = exchanger.exchange(this.list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.afisLista(); // display list after exchange
    }
}

public class ExchangerTestSimple {
    public static void main(String args[]) {
        Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        Fir f1 = new Fir(1000, exchanger, "Duke");
        Fir f2 = new Fir(5000, exchanger, "Wild Wings");
        f1.start();
        f2.start();
    }
}
