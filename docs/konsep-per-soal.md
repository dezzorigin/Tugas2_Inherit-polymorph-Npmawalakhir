# Konsep per soal

Tugas 2 Praktikum PBO, kelas A. Empat soal, masing-masing di package sendiri
(`soal1`–`soal4`). Ringkasan di bawah ini menjelaskan konsep apa yang
didemonstrasikan tiap soal; jawaban analisis lengkap ada di `README.md`.

## Cara compile & run

Butuh JDK 21 (atau lebih baru). Dari root repo:

```sh
find src -name "*.java" > sources.txt
javac -d out @sources.txt
```

Lalu jalankan per soal:

```sh
java -cp out soal1.TesSoal1
java -cp out soal2.TesSoal2            # berhenti di tengah dengan ClassCastException — memang begitu yang diminta soal
java -cp out soal2.TesSoal2Perbaikan
java -cp out soal2.TesSoal2B
java -cp out soal2.TesSoal2C
java -cp out soal3.TesSoal3
java -cp out soal4.TesSoal4
```

Output lengkap tiap program ada di `README.md` (sudah dijalankan, bukan karangan).
Diagram kelas sederhana: [`diagram-kelas.html`](diagram-kelas.html).

## Soal 1 — Inheritance + overriding + protected

Hierarki: `Perangkat` → `Laptop` → `LaptopGaming`, dan `Perangkat` → `Ponsel`.

- **Inheritance (is-a):** Laptop *adalah* Perangkat, LaptopGaming *adalah* Laptop.
  `LaptopGaming` menambah garansi 6 bulan lewat konstruktor (`super(...)`
  dengan `garansiBulan + 6`).
- **Overriding:** `infoSingkat()` ditulis ulang di tiap subclass, memanggil
  `super.infoSingkat()` lalu menambah detailnya sendiri (RAM/layar, GPU,
  kapasitas/kamera).
- **protected vs private:** `harga` di `Perangkat` sengaja `protected`, jadi
  `Laptop.hitungHargaPromo()` bisa membacanya langsung. Kalau `harga` diubah
  jadi `private`, compile gagal (`harga has private access in Perangkat`) —
  lihat bagian "Soal 1 — tambahan" di `README.md`.
- **Konstanta:** tarif pajak 10% (`TARIF_PAJAK`) dan tambahan garansi
  (`TAMBAHAN_GARANSI_BULAN`) disimpan sebagai `static final`.

## Soal 2 — Tipe statis vs dinamis, downcasting, urutan inisialisasi, LSP

Tiga bagian:

- **Bagian A — dynamic dispatch & downcasting:** `Hewan h = new Kucing("Tom")`.
  `h.makan()` memanggil `suara()` versi Kucing ("meong") walaupun `makan()`
  ditulis di `Hewan` — implementasi yang jalan ditentukan tipe dinamis
  (objek aslinya), bukan tipe statis variabelnya. `h.panjat()` ditolak
  compiler karena tipe statis `h` adalah `Hewan` yang tidak punya `panjat()`.
  Downcast `Hewan` biasa ke `Kucing` melempar `ClassCastException` saat
  runtime; perbaikannya cek dulu dengan `instanceof`
  (lihat `TesSoal2Perbaikan`).
- **Bagian B — konstruktor memanggil method yang bisa di-override:**
  konstruktor `A` memanggil `init()`, yang di-override di `B`. Waktu
  `super()` berjalan, field `nilai` di `B` belum diinisialisasi (masih 0),
  jadi baris "init B" mencetak `nilai=0`. Pelajarannya: jangan panggil method
  yang bisa di-override dari konstruktor.
- **Bagian C — pelanggaran Liskov Substitution Principle (LSP):**
  `Titik extends Lingkaran` dengan jari-jari 0 dan `luas()` 0.0. "Titik
  adalah sebuah Lingkaran" tidak masuk akal sebagai relasi is-a: kode yang
  menerima `Lingkaran` dan menganggap luasnya positif akan salah hitung
  kalau diberi `Titik`.

## Soal 3 — Composition + defensive copy (aliasing)

`Garis` **punya** dua `Titik` (has-a), bukan **adalah** Titik — jadi komposisi,
bukan inheritance. Konstruktor, getter, dan setter semuanya membuat salinan
baru (`new Titik(...)`) supaya objek luar tidak bisa mengubah isi `Garis`
lewat referensi yang sama (aliasing). Buktinya di `TesSoal3`: `t.setX(100)`
setelah `g2` dibuat tidak mengubah `g2` — baris kedua tetap
`(1.0, 1.0) - (5.0, 5.0)`. `getPanjang()` memakai `jarakKe()` milik `Titik`
(komposisi memakai kemampuan objek lain). Ada dua konstruktor (overloading):
dari dua `Titik`, atau dari empat koordinat.

## Soal 4 — Polimorfisme + abstract class + instanceof yang aman

Hierarki: `Produk` (abstract) → `ProdukFisik` dan `ProdukDigital`;
`ProdukDigital` → `Langganan`.

- **Polimorfisme:** `Keranjang.cetakStruk()` dan `hitungTotal()` meloop daftar
  bertipe `Produk` dan hanya memanggil `deskripsi()` /
  `hitungHargaAkhir()` yang di-override tiap jenis — tanpa `instanceof`.
  Java memilih implementasi lewat tipe dinamis tiap produk.
- **instanceof yang aman:** `hitungPerJenis()` butuh tahu jenis asli tiap
  produk, jadi memakai `instanceof` + downcast setelah dicek. Urutannya
  penting: `Langganan` dicek **dulu** karena ia juga `ProdukDigital` —
  kalau dibalik, langganan terhitung sebagai digital.
- **Aturan bisnis sebagai konstanta:** ongkir Rp10.000/kg, diskon digital
  10%, diskon langganan 20% untuk durasi ≥ 12 bulan (langganan tidak memakai
  diskon digital). Kode ganda ditolak `tambah()` dengan
  `IllegalStateException`; harga dasar negatif atau nama kosong ditolak
  konstruktor dengan `IllegalArgumentException`.
