package com.bank.app.accounts;

import com.bank.app.cards.KrediKarti;

/**
 * VadesizHesap sınıfı - BankaHesabi sınıfından miras alır.
 * Para transferi ve kredi kartı borç ödeme işlemlerini gerçekleştirir.
 */
public class VadesizHesap extends BankaHesabi {

    // Hesabın türünü belirten alan
    private String hesapTuru;

    /**
     * VadesizHesap sınıfının parametreli constructor'ı.
     *
     * @param bakiye Hesabın başlangıç bakiyesi
     */
    public VadesizHesap(double bakiye) {
        // Üst sınıfın (BankaHesabi) constructor'ını çağırarak IBAN ve bakiyeyi başlatıyoruz
        super(bakiye);
        this.hesapTuru = "Vadesiz Hesap";
    }

    // --- Getter ve Setter Metotları ---

    public String getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    /**
     * İki hesap arasında para transferi yapar.
     * Gönderen hesabın bakiyesinden miktar düşülür, alıcı hesabın bakiyesine eklenir.
     *
     * @param aliciHesap    Para transferinin alıcı hesabı
     * @param gonderenHesap Para transferinin gönderen hesabı
     * @param miktar        Transfer edilecek miktar
     */
    public void paraTransferi(BankaHesabi aliciHesap, BankaHesabi gonderenHesap, double miktar) {
        // Gönderen hesabın bakiyesi kontrol ediliyor
        if (gonderenHesap.getBakiye() >= miktar) {
            // Gönderen hesaptan miktar düşülüyor
            gonderenHesap.setBakiye(gonderenHesap.getBakiye() - miktar);
            // Alıcı hesaba miktar ekleniyor
            aliciHesap.setBakiye(aliciHesap.getBakiye() + miktar);
            System.out.println("Para transferi başarılı! " + miktar + " TL transfer edildi.");
            System.out.println("Gönderen Hesap Bakiye: " + gonderenHesap.getBakiye() + " TL");
            System.out.println("Alıcı Hesap Bakiye: " + aliciHesap.getBakiye() + " TL");
        } else {
            // Yetersiz bakiye durumunda uyarı verilir
            System.out.println("Yetersiz bakiye! Transfer gerçekleştirilemedi.");
        }
    }

    /**
     * Kredi kartı borcunu vadesiz hesaptan öder.
     * Hesabın bakiyesinden ödeme miktarı düşülür, kartın güncel borcu azaltılır.
     *
     * @param kart   Borcu ödenecek kredi kartı
     * @param miktar Ödenecek miktar
     */
    public void krediKartiBorcOdeme(KrediKarti kart, double miktar) {
        // Hesap bakiyesinin yeterli olup olmadığı kontrol ediliyor
        if (this.getBakiye() >= miktar) {
            // Ödeme miktarının kartın güncel borcundan fazla olup olmadığı kontrol ediliyor
            if (miktar <= kart.getGuncelBorc()) {
                // Hesap bakiyesinden miktar düşülüyor
                this.setBakiye(this.getBakiye() - miktar);
                // Kartın güncel borcu azaltılıyor
                kart.setGuncelBorc(kart.getGuncelBorc() - miktar);
                System.out.println("Kredi kartı borç ödemesi başarılı! " + miktar + " TL ödendi.");
                System.out.println("Hesap Bakiye: " + this.getBakiye() + " TL");
                System.out.println("Kart Güncel Borç: " + kart.getGuncelBorc() + " TL");
            } else {
                System.out.println("Ödeme miktarı güncel borçtan fazla olamaz!");
            }
        } else {
            System.out.println("Yetersiz bakiye! Borç ödemesi gerçekleştirilemedi.");
        }
    }

    /**
     * Vadesiz hesap bilgilerini String olarak döndürür.
     *
     * @return Hesap bilgileri
     */
    @Override
    public String toString() {
        return "VadesizHesap [Tür: " + hesapTuru + ", IBAN: " + getIban()
                + ", Bakiye: " + getBakiye() + " TL]";
    }
}
