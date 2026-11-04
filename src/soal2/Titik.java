package soal2;

/** Titik, subclass Lingkaran dengan jari-jari 0. */
public class Titik extends Lingkaran {
    public Titik() {
        super(0);
    }

    @Override
    public double luas() {
        return 0.0;
    }

    @Override
    public double keliling() {
        return 0.0;
    }
}
