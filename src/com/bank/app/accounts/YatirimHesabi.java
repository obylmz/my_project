package com.bank.app.accounts;

/**
 * YatirimHesabi sınıfı - BankaHesabi sınıfından miras alır.
 * Yatırım hesabına para ekleme ve para çekme işlemlerini gerçekleştirir.
 */
public class YatirimHesabi extends BankaHesabi {

    // Hesabın türünü belirten alan
    private String hesapTuru;

    /**
     * YatirimHesabi sınıfının parametreli constructor'ı.
     *
     * @param bakiye Hesabın başlangıç bakiyesi
     */
    public YatirimHesabi(double bakiye) {
        // Üst sınıfın (BankaHesabi) constructor'ını çağırarak IBAN ve bakiyeyi başlatıyoruz
        super(bakiye);
        this.hesapTuru = "Yatırım Hesabı";
    }

    // --- Getter ve Setter Metotları ---

    public String getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    /**
     * Yatırım hesabına para ekler.
     * Belirtilen miktar hesabın bakiyesine eklenir.
     *
     * @param miktar Eklenecek para miktarı
     */
    public void paraEkle(double miktar) {
        // Miktarın pozitif olup olmadığı kontrol ediliyor
        if (miktar > 0) {
            // Hesap bakiyesine miktar ekleniyor
            this.setBakiye(this.getBakiye() + miktar);
            System.out.println(miktar + " TL yatırım hesabına eklendi.");
            System.out.println("Güncel Bakiye: " + this.getBakiye() + " TL");
        } else {
            System.out.println("Eklenecek miktar 0'dan büyük olmalıdır!");
        }
    }

    /**
     * Yatırım hesabından para çeker.
     * Belirtilen miktar hesabın bakiyesinden düşülür.
     *
     * @param miktar Çekilecek para miktarı
     */
    public void paraCek(double miktar) {
        // Hesap bakiyesinin yeterli olup olmadığı kontrol ediliyor
        if (miktar > 0 && this.getBakiye() >= miktar) {
            // Hesap bakiyesinden miktar düşülüyor
            this.setBakiye(this.getBakiye() - miktar);
            System.out.println(miktar + " TL yatırım hesabından çekildi.");
            System.out.println("Güncel Bakiye: " + this.getBakiye() + " TL");
        } else if (miktar <= 0) {
            System.out.println("Çekilecek miktar 0'dan büyük olmalıdır!");
        } else {
            System.out.println("Yetersiz bakiye! Çekme işlemi gerçekleştirilemedi.");
        }
    }

    /**
     * Yatırım hesabı bilgilerini String olarak döndürür.
     *
     * @return Hesap bilgileri
     */
    @Override
    public String toString() {
        return "YatirimHesabi [Tür: " + hesapTuru + ", IBAN: " + getIban()
                + ", Bakiye: " + getBakiye() + " TL]";
    }
}
