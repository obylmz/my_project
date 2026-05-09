package com.bank.app.people;

import java.util.ArrayList;
import java.util.Random;

/**
 * BankaPersoneli sınıfı - Kisi sınıfından miras alır.
 * Banka çalışanlarının bilgilerini ve temsilcisi oldukları müşteri listesini tutar.
 */
public class BankaPersoneli extends Kisi {

    // Personelin benzersiz kimlik numarası (otomatik üretilir)
    private String personelID;

    // Personelin temsilcisi olduğu müşterilerin listesi
    private ArrayList<Musteri> musteriler;

    /**
     * BankaPersoneli sınıfının parametreli constructor'ı.
     * personelID otomatik olarak random number generator ile üretilir.
     *
     * @param ad              Personelin adı
     * @param soyad           Personelin soyadı
     * @param email           Personelin e-posta adresi
     * @param telefonNumarasi Personelin telefon numarası
     */
    public BankaPersoneli(String ad, String soyad, String email, int telefonNumarasi) {
        // Üst sınıfın (Kisi) constructor'ını çağırarak ortak alanları başlatıyoruz
        super(ad, soyad, email, telefonNumarasi);

        // Random number generator ile 6 haneli benzersiz personelID oluşturuyoruz
        Random random = new Random();
        this.personelID = "P" + (100000 + random.nextInt(900000));

        // Müşteri listesini boş ArrayList olarak başlatıyoruz
        this.musteriler = new ArrayList<>();
    }

    // --- Getter ve Setter Metotları ---

    public String getPersonelID() {
        return personelID;
    }

    public void setPersonelID(String personelID) {
        this.personelID = personelID;
    }

    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    public void setMusteriler(ArrayList<Musteri> musteriler) {
        this.musteriler = musteriler;
    }

    /**
     * Personele yeni bir müşteri ekler.
     *
     * @param musteri Eklenecek müşteri nesnesi
     */
    public void musteriEkle(Musteri musteri) {
        this.musteriler.add(musteri);
        System.out.println("Müşteri '" + musteri.getAd() + " " + musteri.getSoyad()
                + "' personel '" + this.getAd() + " " + this.getSoyad() + "' temsilciliğine eklendi.");
    }

    /**
     * Personelin bilgilerini ve müşteri sayısını String olarak döndürür.
     *
     * @return Personel bilgileri
     */
    @Override
    public String toString() {
        return "BankaPersoneli [PersonelID: " + personelID + ", " + super.toString()
                + ", Müşteri Sayısı: " + musteriler.size() + "]";
    }
}
