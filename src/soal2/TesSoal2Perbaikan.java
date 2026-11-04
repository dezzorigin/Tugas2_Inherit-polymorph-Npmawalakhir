package soal2;

/** Perbaikan bagian yang menyebabkan ClassCastException di TesSoal2. */
public class TesSoal2Perbaikan {
    public static void main(String[] args) {
        Hewan h2 = new Hewan("Generik");
        if (h2 instanceof Kucing) {
            Kucing k2 = (Kucing) h2;
            k2.panjat();
        } else {
            System.out.println("h2 bukan Kucing, jadi tidak bisa dipanjat");
        }
    }
}
