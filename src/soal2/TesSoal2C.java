package soal2;

/** Uji soal 2 bagian C. */
public class TesSoal2C {
    public static void main(String[] args) {
        Lingkaran l1 = new Lingkaran(5);
        Titik l2 = new Titik();
        Lingkaran l3 = new Titik();
        System.out.println(l1.luas());
        System.out.println(l2.luas());
        System.out.println(l3.luas());
    }
}
