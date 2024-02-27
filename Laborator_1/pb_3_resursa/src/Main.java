class Resursa {
    private int res;
    private boolean disponibil = false;

    synchronized int citeste() {
        while (!disponibil) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Eroare la așteptare: " + e.getMessage());
            }
        }
        System.out.println("Am citit: " + res);
        disponibil = false;
        notify();
        return res;
    }

    synchronized void scrie(int r) {
        while (disponibil) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Eroare la așteptare: " + e.getMessage());
            }
        }
        res = r;
        disponibil = true;
        System.out.println("Am scris: " + r);
        notify();
    }
}

class FirC extends Thread {
    private Resursa r;

    FirC(Resursa r) {
        this.r = r;
    }

    public void run() {
        for (int i = 0; i <= 10; i++) {
            r.citeste();
        }
    }
}

class FirS extends Thread {
    private Resursa r;

    FirS(Resursa r) {
        this.r = r;
    }

    public void run() {
        for (int i = 0; i <= 10; i++) {
            r.scrie(i);
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Resursa resursa = new Resursa();
        FirS f1 = new FirS(resursa);
        FirC f2 = new FirC(resursa);
        f1.start();
        f2.start();
    }
}
