package soal4;

import java.util.ArrayList;

/** Keranjang belanja berisi produk apa saja. */
public class Keranjang {
    private ArrayList<Produk> daftar = new ArrayList<>();

    /** Tambah produk, kode tidak boleh ganda. */
    public void tambah(Produk produk) {
        for (Produk p : daftar) {
            if (p.getKode().equals(produk.getKode())) {
                throw new IllegalStateException("Kode produk " + produk.getKode() + " sudah ada di keranjang");
            }
        }
        daftar.add(produk);
    }

    /** Total harga akhir semua produk. */
    public double hitungTotal() {
        double total = 0;
        for (Produk p : daftar) {
            total += p.hitungHargaAkhir();
        }
        return total;
    }

    /** Cetak struk, polimorfisme yang menentukan harga dan deskripsi. */
    public void cetakStruk() {
        for (Produk p : daftar) {
            System.out.printf("%s | %s | %s | Rp%.0f%n", p.getKode(), p.getNama(), p.deskripsi(), p.hitungHargaAkhir());
        }
        System.out.printf("TOTAL: Rp%.0f%n", hitungTotal());
    }

    /** Hitung jumlah per jenis. Langganan dicek dulu karena ia juga ProdukDigital. */
    public void hitungPerJenis() {
        int fisik = 0;
        int digital = 0;
        int langganan = 0;
        for (Produk p : daftar) {
            if (p instanceof Langganan) {
                Langganan l = (Langganan) p;
                langganan++;
            } else if (p instanceof ProdukDigital) {
                ProdukDigital d = (ProdukDigital) p;
                digital++;
            } else if (p instanceof ProdukFisik) {
                ProdukFisik f = (ProdukFisik) p;
                fisik++;
            }
        }
        System.out.printf("Fisik: %d, Digital: %d, Langganan: %d%n", fisik, digital, langganan);
    }
}
