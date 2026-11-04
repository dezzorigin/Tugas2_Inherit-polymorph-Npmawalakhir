package soal4;

/** Produk fisik, harga akhir ditambah ongkir per kg. */
public class ProdukFisik extends Produk {
    /** Ongkir Rp10.000 per kg. */
    public static final double ONGKIR_PER_KG = 10000;

    private double beratKg;

    public ProdukFisik(String kode, String nama, double hargaDasar, double beratKg) {
        super(kode, nama, hargaDasar);
        this.beratKg = beratKg;
    }

    @Override
    public double hitungHargaAkhir() {
        return getHargaDasar() + beratKg * ONGKIR_PER_KG;
    }

    @Override
    public String deskripsi() {
        return "fisik " + beratKg + " kg";
    }
}
