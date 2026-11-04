package soal1;

/** Laptop gaming, subclass dari Laptop dengan garansi tambahan. */
public class LaptopGaming extends Laptop {
    /** Tambahan garansi 6 bulan. */
    public static final int TAMBAHAN_GARANSI_BULAN = 6;

    private String kartuGrafis;

    public LaptopGaming(String merek, double harga, int garansiBulan, int ramGb,
                        double ukuranLayarInci, String kartuGrafis) {
        super(merek, harga, garansiBulan + TAMBAHAN_GARANSI_BULAN, ramGb, ukuranLayarInci);
        this.kartuGrafis = kartuGrafis;
    }

    @Override
    public String infoSingkat() {
        return super.infoSingkat() + " | GPU " + kartuGrafis;
    }
}
