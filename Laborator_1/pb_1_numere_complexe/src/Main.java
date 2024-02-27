class NumarComplex {
    double real;
    double imaginar;

    public NumarComplex(double real, double imaginar) {
        this.real = real;
        this.imaginar = imaginar;
    }


    public NumarComplex aduna(NumarComplex numar) {
        double realSuma = this.real + numar.real;
        double imaginarSuma = this.imaginar + numar.imaginar;
        return new NumarComplex(realSuma, imaginarSuma);
    }

    // Metoda pentru înmulțirea a două numere complexe
    public NumarComplex inmulteste(NumarComplex numar) {
        double realProdus = this.real * numar.real - this.imaginar * numar.imaginar;
        double imaginarProdus = this.real * numar.imaginar + this.imaginar * numar.real;
        return new NumarComplex(realProdus, imaginarProdus);
    }

    // Metoda pentru afișarea numărului complex
    public void afiseaza() {
        System.out.println(this.real + " + " + this.imaginar + "i");
    }
}

public class Main {
    public static void main(String[] args) {

        NumarComplex numar1 = new NumarComplex(2, 5);
        NumarComplex numar2 = new NumarComplex(4, -1);


        System.out.println("Suma:");
        NumarComplex suma = numar1.aduna(numar2);
        suma.afiseaza();


        System.out.println("Produsul:");
        NumarComplex produs = numar1.inmulteste(numar2);
        produs.afiseaza();
    }
}
