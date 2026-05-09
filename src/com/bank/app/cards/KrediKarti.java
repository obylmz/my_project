package com.bank.app.cards;

import java.util.Random;

/**
 * KrediKarti sınıfı - Müşterilerin kredi kartı bilgilerini tutar.
 * Kartın limiti, güncel borcu ve kullanılabilir limiti yönetilir.
 */
public class KrediKarti {

    // Kredi kartının benzersiz numarası (otomatik üretilir)
    private String kartNumarasi;

    // Kredi kartının toplam limiti
    private double limit;

    // Kartın güncel borcu
    private double guncelBorc;

    // Kartın kullanılabilir limiti (limit - guncelBorc)
    private double kullanilabilirLimit;

    /**
     * KrediKarti sınıfının parametreli constructor'ı.
     * kartNumarasi otomatik olarak random number generator ile üretilir.
     * Kullanılabilir limit, limit ve güncel borç farkından hesaplanır.
     *
     * @param limit     Kredi kartının toplam limiti
     * @param guncelBorc Kartın güncel borcu
     */
    public KrediKarti(double limit, double guncelBorc) {
        // Random number generator ile 16 haneli benzersiz kart numarası oluşturuyoruz
        Random random = new Random();
        StringBuilder kartNo = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            kartNo.append(random.nextInt(10));
        }
        this.kartNumarasi = kartNo.toString();

        this.limit = limit;
        this.guncelBorc = guncelBorc;

        // Kullanılabilir limit = Toplam limit - Güncel borç
        this.kullanilabilirLimit = limit - guncelBorc;
    }

    // --- Getter ve Setter Metotları ---

    public String getKartNumarasi() {
        return kartNumarasi;
    }

    public void setKartNumarasi(String kartNumarasi) {
        this.kartNumarasi = kartNumarasi;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getGuncelBorc() {
        return guncelBorc;
    }

    /**
     * Kartın güncel borcunu günceller ve kullanılabilir limiti yeniden hesaplar.
     *
     * @param guncelBorc Yeni güncel borç değeri
     */
    public void setGuncelBorc(double guncelBorc) {
        this.guncelBorc = guncelBorc;
        // Kullanılabilir limit güncelleniyor
        this.kullanilabilirLimit = this.limit - this.guncelBorc;
    }

    public double getKullanilabilirLimit() {
        return kullanilabilirLimit;
    }

    public void setKullanilabilirLimit(double kullanilabilirLimit) {
        this.kullanilabilirLimit = kullanilabilirLimit;
    }

    /**
     * Kredi kartı bilgilerini String olarak döndürür.
     *
     * @return Kart bilgileri
     */
    @Override
    public String toString() {
        return "KrediKarti [Kart No: " + kartNumarasi
                + ", Limit: " + limit + " TL"
                + ", Güncel Borç: " + guncelBorc + " TL"
                + ", Kullanılabilir Limit: " + kullanilabilirLimit + " TL]";
    }
}
