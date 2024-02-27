class Fir extends Thread {
    public int pas;
    public int suma;

    public Fir(int pas) {
        this.pas = pas;
        this.suma = 0;
    }

    public void run() {
        for (int i = 0; i <= 30; i = i + pas) {
            suma = suma + i;
        }
        System.out.println("Suma pentru " + this.getName() + " = " + suma );
    }


}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fir fir1 = new Fir(2);
        Fir fir2 = new Fir(3);
        Fir fir3 = new Fir(4);

        fir1.start();
        fir2.start();
        fir3.start();

        fir1.join();
        fir2.join();
        fir3.join();

        Thread thread1 = new Thread(fir1);
        Thread thread2 = new Thread(fir2);
        Thread thread3 = new Thread(fir3);

        thread1.setPriority(10);


        System.out.println("Prioritatea firului 1: " + thread1.getPriority());
        System.out.println("Prioritatea firului 2: " + thread2.getPriority());
        System.out.println("Prioritatea firului 3: " + thread3.getPriority());

        int sumaTotala = fir1.suma + fir2.suma + fir3.suma;

        System.out.println("Suma totala a valorilor calculate de cele 3 fire este: " + sumaTotala);
    }
}
