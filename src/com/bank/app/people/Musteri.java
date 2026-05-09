package com.bank.app.people;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;

import java.util.ArrayList;
import java.util.Random;

/**
 * Musteri sınıfı - Kisi sınıfından miras alır.
 * Müşterinin hesaplarını ve kredi kartlarını yönetir.
 */
public class Musteri extends Kisi {

    // Müşterinin benzersiz numarası (otomatik üretilir)
    private String musteriNumarasi;

    // Müşterinin sahip olduğu banka hesaplarının listesi
    private ArrayList<BankaHesabi> hesaplar;

    // Müşterinin sahip olduğu kredi kartlarının listesi
    private ArrayList<KrediKarti> krediKartlari;

    /**
     * Musteri sınıfının parametreli constructor'ı.
     * musteriNumarasi otomatik olarak random number generator ile üretilir.
     *
     * @param ad              Müşterinin adı
     * @param soyad           Müşterinin soyadı
     * @param email           Müşterinin e-posta adresi
     * @param telefonNumarasi Müşterinin telefon numarası
     */
    public Musteri(String ad, String soyad, String email, int telefonNumarasi) {
        // Üst sınıfın (Kisi) constructor'ını çağırarak ortak alanları başlatıyoruz
        super(ad, soyad, email, telefonNumarasi);

        // Random number generator ile 8 haneli benzersiz müşteri numarası oluşturuyoruz
        Random random = new Random();
        this.musteriNumarasi = "M" + (10000000 + random.nextInt(90000000));

        // Hesap ve kredi kartı listelerini boş ArrayList olarak başlatıyoruz
        this.hesaplar = new ArrayList<>();
        this.krediKartlari = new ArrayList<>();
    }

    // --- Getter ve Setter Metotları ---

    public String getMusteriNumarasi() {
        return musteriNumarasi;
    }

    public void setMusteriNumarasi(String musteriNumarasi) {
        this.musteriNumarasi = musteriNumarasi;
    }

    public ArrayList<BankaHesabi> getHesaplar() {
        return hesaplar;
    }

    public void setHesaplar(ArrayList<BankaHesabi> hesaplar) {
        this.hesaplar = hesaplar;
    }

    public ArrayList<KrediKarti> getKrediKartlari() {
        return krediKartlari;
    }

    public void setKrediKartlari(ArrayList<KrediKarti> krediKartlari) {
        this.krediKartlari = krediKartlari;
    }

    /**
     * Müşteriye yeni bir hesap ekler.
     * Hesap türüne göre (vadesiz veya yatırım) ilgili sınıftan nesne oluşturur
     * ve hesaplar ArrayList'ine ekler.
     *
     * @param hesapTuru Açılacak hesap türü ("vadesiz" veya "yatirim")
     * @param bakiye    Hesabın başlangıç bakiyesi
     */
    public void hesapEkle(String hesapTuru, double bakiye) {
        // Hesap türüne göre ilgili sınıftan nesne oluşturuyoruz
        if (hesapTuru.equalsIgnoreCase("vadesiz")) {
            VadesizHesap vadesizHesap = new VadesizHesap(bakiye);
            hesaplar.add(vadesizHesap);
            System.out.println("Vadesiz hesap oluşturuldu. IBAN: " + vadesizHesap.getIban());
        } else if (hesapTuru.equalsIgnoreCase("yatirim")) {
            YatirimHesabi yatirimHesabi = new YatirimHesabi(bakiye);
            hesaplar.add(yatirimHesabi);
            System.out.println("Yatırım hesabı oluşturuldu. IBAN: " + yatirimHesabi.getIban());
        } else {
            // Geçersiz hesap türü girilmişse uyarı verilir
            System.out.println("Geçersiz hesap türü! 'vadesiz' veya 'yatirim' giriniz.");
        }
    }

    /**
     * Müşteriye yeni bir kredi kartı ekler.
     * KrediKarti sınıfından bir nesne oluşturur ve krediKartlari ArrayList'ine ekler.
     *
     * @param limit     Kredi kartı limiti
     * @param guncelBorc Kartın güncel borcu (genellikle 0 ile başlar)
     */
    public void krediKartiEkle(double limit) {
        // Yeni kredi kartı oluşturuyoruz (başlangıç borcu 0)
        KrediKarti krediKarti = new KrediKarti(limit, 0);
        krediKartlari.add(krediKarti);
        System.out.println("Kredi kartı oluşturuldu. Kart No: " + krediKarti.getKartNumarasi()
                + ", Limit: " + limit + " TL");
    }

    /**
     * Müşterinin hesabını siler.
     * Önce hesabın bakiyesini kontrol eder.
     * Bakiye 0'dan büyükse uyarı verir, 0'a eşitse hesabı siler.
     *
     * @param hesap Silinecek hesap nesnesi
     */
    public void hesapSil(BankaHesabi hesap) {
        // Hesabın bakiyesi kontrol ediliyor
        if (hesap.getBakiye() > 0) {
            // Bakiye varsa silme işlemi yapılmaz, kullanıcıya uyarı verilir
            System.out.println("Lütfen öncelikle bakiyenizi başka bir hesaba aktarınız.");
        } else {
            // Bakiye 0 ise hesap listeden siliniyor
            hesaplar.remove(hesap);
            System.out.println("Hesap başarıyla silindi. IBAN: " + hesap.getIban());
        }
    }

    /**
     * Müşterinin kredi kartını siler.
     * Önce kartın güncel borcunu kontrol eder.
     * Borç 0'a eşit değilse uyarı verir, 0'a eşitse kartı siler.
     *
     * @param kart Silinecek kredi kartı nesnesi
     */
    public void krediKartiSil(KrediKarti kart) {
        // Kartın güncel borcu kontrol ediliyor
        if (kart.getGuncelBorc() != 0) {
            // Borç varsa silme işlemi yapılmaz, kullanıcıya uyarı verilir
            System.out.println("Lütfen öncelikle borç ödemesi yapınız.");
        } else {
            // Borç yoksa kart listeden siliniyor
            krediKartlari.remove(kart);
            System.out.println("Kredi kartı başarıyla silindi. Kart No: " + kart.getKartNumarasi());
        }
    }

    /**
     * Müşteri bilgilerini, hesap ve kart sayısını String olarak döndürür.
     *
     * @return Müşteri bilgileri
     */
    @Override
    public String toString() {
        return "Musteri [MusteriNo: " + musteriNumarasi + ", " + super.toString()
                + ", Hesap Sayısı: " + hesaplar.size()
                + ", Kredi Kartı Sayısı: " + krediKartlari.size() + "]";
    }
}
