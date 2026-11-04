package soal4;

/** Kelas induk semua produk. */
public abstract class Produk {
    private String kode;
    private String nama;
    private double hargaDasar;

    public Produk(String kode, String nama, double hargaDasar) {
        if (nama == null || nama.isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (hargaDasar < 0) {
            throw new IllegalArgumentException("Harga dasar tidak boleh negatif");
        }
        this.kode = kode;
        this.nama = nama;
        this.hargaDasar = hargaDasar;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    /** Harga akhir setelah penyesuaian tiap jenis produk. */
    public abstract double hitungHargaAkhir();

    /** Deskripsi jenis produk, mis. "fisik 1.5 kg". */
    public abstract String deskripsi();
}
