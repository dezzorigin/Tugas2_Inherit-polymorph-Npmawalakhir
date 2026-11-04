package soal4;

/** Produk digital, harga akhir dipotong diskon digital. */
public class ProdukDigital extends Produk {
    /** Diskon digital 10%. */
    public static final double DISKON_DIGITAL = 0.10;

    private double ukuranMb;

    public ProdukDigital(String kode, String nama, double hargaDasar, double ukuranMb) {
        super(kode, nama, hargaDasar);
        this.ukuranMb = ukuranMb;
    }

    public double getUkuranMb() {
        return ukuranMb;
    }

    @Override
    public double hitungHargaAkhir() {
        return getHargaDasar() * (1 - DISKON_DIGITAL);
    }

    @Override
    public String deskripsi() {
        return "digital " + ukuranMb + " MB";
    }
}
