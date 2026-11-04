package soal2;

/** Uji soal 2 bagian A, sesuai kode dari soal. */
public class TesSoal2 {
    public static void main(String[] args) {
        Hewan h = new Kucing("Tom");
        h.makan();
        h.suara();
        // h.panjat();
        Kucing k = (Kucing) h;
        k.panjat();
        Hewan h2 = new Hewan("Generik");
        Kucing k2 = (Kucing) h2;
        k2.panjat();
    }
}
