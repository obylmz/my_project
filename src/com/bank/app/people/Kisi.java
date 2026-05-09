package com.bank.app.people;

/**
 * Kisi sınıfı - Banka personeli ve müşterilerin ortak özelliklerini barındıran üst sınıf.
 * BankaPersoneli ve Musteri sınıfları bu sınıftan miras alır.
 */
public class Kisi {

    // Kişinin adı
    private String ad;

    // Kişinin soyadı
    private String soyad;

    // Kişinin e-posta adresi
    private String email;

    // Kişinin telefon numarası
    private int telefonNumarasi;

    /**
     * Kisi sınıfının parametreli constructor'ı.
     * Tüm alanları başlatır.
     *
     * @param ad              Kişinin adı
     * @param soyad           Kişinin soyadı
     * @param email           Kişinin e-posta adresi
     * @param telefonNumarasi Kişinin telefon numarası
     */
    public Kisi(String ad, String soyad, String email, int telefonNumarasi) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
    }

    // --- Getter ve Setter Metotları ---

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(int telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    /**
     * Kişinin bilgilerini String olarak döndürür.
     *
     * @return Kişi bilgileri
     */
    @Override
    public String toString() {
        return "Ad: " + ad + ", Soyad: " + soyad + ", Email: " + email
                + ", Telefon: " + telefonNumarasi;
    }
}
