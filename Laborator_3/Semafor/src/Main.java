import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        Fir fir1 = new Fir(2, 5, semaphore, 1, 15);
        Fir fir2 = new Fir(3, 6, semaphore, 1, 10);
//        Fir fir3 = new Fir(4, 7, semaphore, 1, 10);

        fir1.start();
        fir2.start();
//        fir3.start();
    }
}
