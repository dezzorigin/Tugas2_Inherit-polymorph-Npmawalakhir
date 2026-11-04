package soal1;

/**
 * Kelas induk perangkat elektronik.
 */
public class Perangkat {
    /** Tarif pajak 10%. */
    public static final double TARIF_PAJAK = 0.10;

    private String merek;
    protected double harga;
    private int garansiBulan;

    /**
     * @param merek merek perangkat, tidak boleh kosong
     * @param harga harga perangkat, tidak boleh negatif
     * @param garansiBulan lama garansi dalam bulan
     */
    public Perangkat(String merek, double harga, int garansiBulan) {
        if (merek == null || merek.isEmpty()) {
            throw new IllegalArgumentException("Merek tidak boleh kosong");
        }
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        this.merek = merek;
        this.harga = harga;
        this.garansiBulan = garansiBulan;
    }

    public String getMerek() {
        return merek;
    }

    public int getGaransiBulan() {
        return garansiBulan;
    }

    /** Pajak = harga dikali tarif pajak. */
    public double hitungPajak() {
        return harga * TARIF_PAJAK;
    }

    /** Info singkat: merek dan garansi. */
    public String infoSingkat() {
        return merek + " | garansi " + garansiBulan + " bulan";
    }
}
