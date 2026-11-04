# Tugas 2 Praktikum PBO — Inheritance, Composition, dan Polimorfisme

Tugas 2, kelas A. Empat soal, masing-masing di package sendiri (soal1 sampai soal4). README ini berisi cara jalan, jawaban soal-soal analisis, dan analisis konsep. Semua output di bawah ini sudah benar-benar dijalankan, bukan karangan.

## Cara menjalankan

Dari root repo ini:

```
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out soal1.TesSoal1
java -cp out soal2.TesSoal2
java -cp out soal2.TesSoal2Perbaikan
java -cp out soal2.TesSoal2B
java -cp out soal2.TesSoal2C
java -cp out soal3.TesSoal3
java -cp out soal4.TesSoal4
```

Hasilnya:

TesSoal1:

```
Asus | garansi 24 bulan | RAM 16 GB | layar 14.0 inci
Samsung | garansi 12 bulan | 128 GB | 3 kamera
MSI | garansi 18 bulan | RAM 32 GB | layar 15.6 inci | GPU RTX 4060
Pajak laptop: Rp800000
Promo laptop 10 persen: Rp7200000
```

TesSoal2 (program berhenti di tengah, ini memang yang diminta soal):

```
Hewan dibuat: Tom
Kucing dibuat
Tom makan
meong
meong
Tom memanjat
Hewan dibuat: Generik
Exception in thread "main" java.lang.ClassCastException: class soal2.Hewan cannot be cast to class soal2.Kucing (soal2.Hewan and soal2.Kucing are in unnamed module of loader 'app')
```

TesSoal2Perbaikan:

```
Hewan dibuat: Generik
h2 bukan Kucing, jadi tidak bisa dipanjat
```

TesSoal2B:

```
A dibuat
init B, nilai=0
B dibuat, nilai=5
```

TesSoal2C:

```
78.53981633974483
0.0
0.0
```

TesSoal3:

```
(0.0, 0.0) - (3.0, 4.0) panjang=5.0
(1.0, 1.0) - (5.0, 5.0)
```

TesSoal4:

```
F01 | Keyboard Mekanik | fisik 1.5 kg | Rp515000
D01 | Ebook Java | digital 20.0 MB | Rp90000
L01 | Kelas Online | langganan 12 bulan | Rp1440000
TOTAL: Rp2045000
Fisik: 1, Digital: 1, Langganan: 1
Gagal: Kode produk F01 sudah ada di keranjang
```

## Soal 1 — tambahan: harga diubah jadi private

Waktu `harga` di Perangkat diubah dari protected ke private, compile gagal di Laptop:

```
Laptop.java:16: error: harga has private access in Perangkat
        return harga * (1 - diskonPersen / 100);
```

Ini muncul karena protected itu artinya subclass boleh akses langsung, sedangkan private cuma boleh diakses dari kelasnya sendiri. `hitungHargaPromo` di Laptop baca `harga` secara langsung, jadi begitu fieldnya jadi private, akses itu ditolak compiler. Kalau memang mau `harga` jadi private, Laptop harus lewat getter (misalnya `getHarga()`), bukan baca fieldnya langsung.

## Soal 2 — prediksi dan hasil

Prediksi saya sebelum menjalankan, lalu dibandingkan dengan hasil asli:

Bagian A, prediksi output: "Hewan dibuat: Tom", "Kucing dibuat", "Tom makan", "meong", "meong", "Tom memanjat", "Hewan dibuat: Generik", lalu program berhenti karena ClassCastException. Hasil jalan ternyata sama persis.

1. Output lengkap sampai program berhenti ada di bagian hasil TesSoal2 di atas.
2. Kalau komentar di baris `h.panjat();` dihapus, yang muncul error waktu compile, bukan runtime:
   ```
   TesSoal2.java:9: error: cannot find symbol
           h.panjat();
            ^
     symbol:   method panjat()
     location: variable h of type Hewan
   ```
   Alasannya: tipe statis `h` adalah Hewan, dan Hewan tidak punya method `panjat`. Compiler cuma peduli tipe statis, jadi pemanggilan itu ditolak sebelum program jalan.
3. Baris yang menyebabkan ClassCastException adalah `Kucing k2 = (Kucing) h2;` (baris 13 TesSoal2). Perbaikannya pakai instanceof tanpa mengubah tipe `h2` (lihat TesSoal2Perbaikan):
   ```java
   if (h2 instanceof Kucing) {
       Kucing k2 = (Kucing) h2;
       k2.panjat();
   } else {
       System.out.println("h2 bukan Kucing, jadi tidak bisa dipanjat");
   }
   ```
   Karena `h2` aslinya Hewan biasa, cabang else yang jalan.
4. Di `h.makan()`, yang jalan adalah `suara()` versi Kucing ("meong"), padahal `makan()` ditulis di Hewan. Ini karena tipe statis dan tipe dinamis itu beda urusannya: tipe statis `h` (Hewan) cuma menentukan method apa yang boleh dipanggil saat compile, sedangkan tipe dinamis (Kucing, objek aslinya) menentukan implementasi mana yang benar-benar jalan saat runtime. `suara()` di-override di Kucing, jadi dispatch runtime memilih versi Kucing.
5. Bagian B, prediksi output `new B();`: "A dibuat", "init B, nilai=0", "B dibuat, nilai=5". Hasil jalan sama persis. Nilai di baris "init B" bukan 5 karena waktu konstruktor A berjalan (lewat `super()`), field `nilai` di B belum sempat diinisialisasi — inisialisasi field subclass baru jalan setelah konstruktor parent selesai, jadi nilainya masih default 0. Risikonya: method yang bisa di-override jangan dipanggil dari konstruktor, karena method itu bisa jalan di atas objek yang belum jadi sepenuhnya dan membaca state yang masih setengah matang.
6. `l3.luas()` menghasilkan 0.0 karena objeknya memang Titik (tipe dinamis), walaupun variabelnya bertipe Lingkaran. Tipe statis hanya menentukan method apa yang bisa dipanggil, bukan implementasi mana yang dieksekusi. "Titik adalah sebuah Lingkaran" sebagai relasi is-a tidak masuk akal: titik bukan lingkaran, dan Titik melanggar janji Lingkaran, karena lingkaran normal luasnya positif kalau jari-jarinya positif. Akibatnya, kalau ada method yang menerima Lingkaran dan menganggap luasnya selalu lebih dari nol, dia akan dapat 0 dan semua hitungan yang bergantung pada asumsi itu ikut salah.

## Soal 3

1. Prediksi baris kedua sebelum perbaikan: `(100.0, 1.0) - (5.0, 5.0)`. Alasannya, Garis menyimpan referensi Titik yang sama dengan yang di luar, jadi waktu `t.setX(100)` dipanggil, titik awal `g2` ikut berubah. Ini efek aliasing.
2. Setelah konstruktor Garis diubah jadi salinan defensif (membuat Titik baru dari nilai x dan y, bukan menyimpan referensinya), baris kedua menjadi `(1.0, 1.0) - (5.0, 5.0)`. Ini sudah dibuktikan lewat TesSoal3 di atas. Setter dan getter juga dibuat menyalin, supaya tidak ada jalur lain yang membocorkan referensi internal.
3. Perilaku sebelum perbaikan lebih tepat kalau dua objek memang sengaja berbagi satu titik yang sama, misalnya satu titik yang dipantau bersama, atau kalau menyalin tiap kali terlalu mahal untuk data yang besar. Perilaku sesudah perbaikan lebih tepat kalau Garis harus memegang datanya sendiri dan tidak boleh diubah dari luar tanpa sepengetahuannya, misalnya untuk objek nilai atau API yang dipakai banyak pihak.
4. Kalau Garis dibuat dengan `extends Titik`, masalahnya ada di relasi: garis bukan titik, garis itu punya titik. Garis akan mewarisi x dan y yang tidak ada artinya buat garis, API-nya jadi membingungkan (apa maksud `garis.getX()`?), dan Garis bisa dipakai di tempat yang mengharapkan Titik, padahal perilakunya bukan perilaku titik. Komposisi lebih jujur di sini: Garis tersusun dari dua Titik.

## Soal 4 — catatan implementasi

Tarif dan batasnya disimpan sebagai konstanta: ongkir Rp10.000 per kg, diskon digital 10%, diskon langganan 20% untuk durasi minimal 12 bulan, dan Langganan tidak memakai diskon digital. `cetakStruk` dan `hitungTotal` meloop daftar bertipe Produk tanpa instanceof, jadi harga dan deskripsi ditentukan polimorfisme. `hitungPerJenis` memakai instanceof dan downcasting yang aman, dengan urutan cek Langganan dulu, karena Langganan juga merupakan ProdukDigital — kalau urutannya dibalik, langganan akan terhitung sebagai digital. Kode ganda ditolak `tambah` dengan IllegalStateException, sedangkan harga dasar negatif atau nama kosong ditolak konstruktor dengan IllegalArgumentException.

## Analisis konsep

1. Overriding dan overloading itu beda tempat mainnya. Overriding terjadi waktu subclass menulis ulang method parent dengan nama dan parameter yang sama, contohnya `infoSingkat()` di Laptop dan `deskripsi()` di tiap jenis produk; method mana yang jalan diputuskan saat runtime berdasarkan objek aslinya, dan di sinilah polimorfisme bekerja. Overloading terjadi waktu satu kelas punya beberapa method dengan nama sama tapi parameter beda, contohnya dua konstruktor Garis; yang dipilih diputuskan saat compile berdasarkan argumennya. Jadi overriding butuh inheritance dan menggerakkan polimorfisme, sedangkan overloading cuma soal kemudahan pemakaian dan tidak butuh inheritance sama sekali.
2. Inheritance dipilih kalau hubungannya memang is-a dan subclass benar-benar butuh perilaku parent, contohnya Laptop adalah Perangkat dan Langganan adalah ProdukDigital. Komposisi dipilih kalau hubungannya has-a, alias satu objek memakai kemampuan objek lain tanpa menjadi jenisnya, contohnya Garis punya dua Titik dan Keranjang punya daftar Produk. Patokan praktisnya gampang: kalau kalimatnya "adalah" dan substitusinya masuk akal, pakai inheritance; kalau kalimatnya "punya" atau perilakunya cuma dipakai, pakai komposisi. Salah pilih di sini biayanya mahal, karena hierarki yang salah susah dibongkar belakangan.
3. `cetakStruk` tidak butuh instanceof karena dia cuma memanggil method yang sudah di-override, yaitu `deskripsi()` dan `hitungHargaAkhir()`, dan Java sendiri yang memilih versi yang tepat lewat tipe dinamis tiap produk. `hitungPerJenis` justru butuh instanceof karena dia perlu tahu jenis asli tiap produk untuk menghitung per kategori, dan informasi itu tidak tersedia lewat method umum Produk. Downcasting tidak selalu aman karena variabel bertipe parent bisa menunjuk objek apa saja dari turunannya; kalau tebakan jenisnya salah, JVM melempar ClassCastException saat runtime, makanya jenisnya dicek dulu dengan instanceof sebelum di-cast.
