package soal4;

/** Langganan, subclass ProdukDigital dengan harga per bulan. */
public class Langganan extends ProdukDigital {
    /** Batas durasi untuk diskon langganan, 12 bulan. */
    public static final int BATAS_DISKON_BULAN = 12;
    /** Diskon langganan 20%. */
    public static final double DISKON_LANGGANAN = 0.20;

    private int durasiBulan;

    public Langganan(String kode, String nama, double hargaDasar, double ukuranMb, int durasiBulan) {
        super(kode, nama, hargaDasar, ukuranMb);
        this.durasiBulan = durasiBulan;
    }

    @Override
    public double hitungHargaAkhir() {
        double total = getHargaDasar() * durasiBulan;
        if (durasiBulan >= BATAS_DISKON_BULAN) {
            total = total * (1 - DISKON_LANGGANAN);
        }
        return total;
    }

    @Override
    public String deskripsi() {
        return "langganan " + durasiBulan + " bulan";
    }
}
