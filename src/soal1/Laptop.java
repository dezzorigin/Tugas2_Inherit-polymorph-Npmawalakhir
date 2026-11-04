package soal1;

/** Laptop, subclass dari Perangkat. */
public class Laptop extends Perangkat {
    private int ramGb;
    private double ukuranLayarInci;

    public Laptop(String merek, double harga, int garansiBulan, int ramGb, double ukuranLayarInci) {
        super(merek, harga, garansiBulan);
        this.ramGb = ramGb;
        this.ukuranLayarInci = ukuranLayarInci;
    }

    /** Harga setelah diskon, akses langsung ke harga (protected). */
    public double hitungHargaPromo(double diskonPersen) {
        return harga * (1 - diskonPersen / 100);
    }

    @Override
    public String infoSingkat() {
        return super.infoSingkat() + " | RAM " + ramGb + " GB | layar " + ukuranLayarInci + " inci";
    }
}
