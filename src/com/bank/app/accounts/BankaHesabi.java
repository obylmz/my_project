package com.bank.app.accounts;

import java.util.Random;

/**
 * BankaHesabi sınıfı - Tüm hesap türlerinin (VadesizHesap, YatirimHesabi) üst sınıfıdır.
 * Hesabın IBAN ve bakiye bilgilerini tutar.
 */
public class BankaHesabi {

    // Hesabın benzersiz IBAN numarası (otomatik üretilir)
    private String iban;

    // Hesabın güncel bakiyesi
    private double bakiye;

    /**
     * BankaHesabi sınıfının parametreli constructor'ı.
     * IBAN otomatik olarak random number generator ile üretilir.
     *
     * @param bakiye Hesabın başlangıç bakiyesi
     */
    public BankaHesabi(double bakiye) {
        // Random number generator ile TR ile başlayan benzersiz IBAN oluşturuyoruz
        Random random = new Random();
        // Gerçek IBAN formatına yakın bir numara üretiyoruz
        StringBuilder ibanBuilder = new StringBuilder("TR");
        for (int i = 0; i < 24; i++) {
            ibanBuilder.append(random.nextInt(10));
        }
        this.iban = ibanBuilder.toString();
        this.bakiye = bakiye;
    }

    // --- Getter ve Setter Metotları ---

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    /**
     * Hesap bilgilerini String olarak döndürür.
     *
     * @return Hesap bilgileri
     */
    @Override
    public String toString() {
        return "BankaHesabi [IBAN: " + iban + ", Bakiye: " + bakiye + " TL]";
    }
}
