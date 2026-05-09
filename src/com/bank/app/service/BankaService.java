package com.bank.app.service;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;
import com.bank.app.people.BankaPersoneli;
import com.bank.app.people.Musteri;

/**
 * BankaService sınıfı - Banka iş mantığını gerçekleştiren servis sınıfıdır.
 * Müşteri oluşturma, hesap yönetimi, para transferi ve kredi kartı işlemlerini yönetir.
 */
public class BankaService {

    /**
     * Yeni bir müşteri oluşturur.
     *
     * @param ad              Müşterinin adı
     * @param soyad           Müşterinin soyadı
     * @param email           Müşterinin e-posta adresi
     * @param telefonNumarasi Müşterinin telefon numarası
     * @return Oluşturulan Musteri nesnesi
     */
    public Musteri musteriOlustur(String ad, String soyad, String email, int telefonNumarasi) {
        Musteri musteri = new Musteri(ad, soyad, email, telefonNumarasi);
        System.out.println("Yeni müşteri oluşturuldu: " + musteri);
        return musteri;
    }

    /**
     * Yeni bir banka personeli oluşturur.
     *
     * @param ad              Personelin adı
     * @param soyad           Personelin soyadı
     * @param email           Personelin e-posta adresi
     * @param telefonNumarasi Personelin telefon numarası
     * @return Oluşturulan BankaPersoneli nesnesi
     */
    public BankaPersoneli personelOlustur(String ad, String soyad, String email, int telefonNumarasi) {
        BankaPersoneli personel = new BankaPersoneli(ad, soyad, email, telefonNumarasi);
        System.out.println("Yeni personel oluşturuldu: " + personel);
        return personel;
    }

    /**
     * Müşteriye yeni bir hesap açar.
     *
     * @param musteri  Hesap açılacak müşteri
     * @param hesapTuru Hesap türü ("vadesiz" veya "yatirim")
     * @param bakiye   Başlangıç bakiyesi
     */
    public void hesapAc(Musteri musteri, String hesapTuru, double bakiye) {
        musteri.hesapEkle(hesapTuru, bakiye);
    }

    /**
     * Müşteriye kredi kartı tanımlar.
     *
     * @param musteri Kredi kartı tanımlanacak müşteri
     * @param limit   Kredi kartı limiti
     */
    public void krediKartiTanimla(Musteri musteri, double limit) {
        musteri.krediKartiEkle(limit);
    }

    /**
     * İki hesap arasında para transferi gerçekleştirir.
     * Yalnızca vadesiz hesaplar arasında transfer yapılabilir.
     *
     * @param aliciHesap    Alıcı hesap
     * @param gonderenHesap Gönderen hesap (VadesizHesap olmalıdır)
     * @param miktar        Transfer miktarı
     */
    public void paraTransferiYap(BankaHesabi aliciHesap, VadesizHesap gonderenHesap, double miktar) {
        gonderenHesap.paraTransferi(aliciHesap, gonderenHesap, miktar);
    }

    /**
     * Yatırım hesabına para ekler.
     *
     * @param hesap  Para eklenecek yatırım hesabı
     * @param miktar Eklenecek miktar
     */
    public void yatirimHesabinaParaEkle(YatirimHesabi hesap, double miktar) {
        hesap.paraEkle(miktar);
    }

    /**
     * Yatırım hesabından para çeker.
     *
     * @param hesap  Para çekilecek yatırım hesabı
     * @param miktar Çekilecek miktar
     */
    public void yatirimHesabindanParaCek(YatirimHesabi hesap, double miktar) {
        hesap.paraCek(miktar);
    }

    /**
     * Kredi kartı borcunu vadesiz hesaptan öder.
     *
     * @param hesap  Ödemenin yapılacağı vadesiz hesap
     * @param kart   Borcu ödenecek kredi kartı
     * @param miktar Ödenecek miktar
     */
    public void krediKartiBorcuOde(VadesizHesap hesap, KrediKarti kart, double miktar) {
        hesap.krediKartiBorcOdeme(kart, miktar);
    }

    /**
     * Müşterinin hesabını siler.
     *
     * @param musteri Hesap silinecek müşteri
     * @param hesap   Silinecek hesap
     */
    public void hesapSil(Musteri musteri, BankaHesabi hesap) {
        musteri.hesapSil(hesap);
    }

    /**
     * Müşterinin kredi kartını siler.
     *
     * @param musteri Kart silinecek müşteri
     * @param kart    Silinecek kredi kartı
     */
    public void krediKartiSil(Musteri musteri, KrediKarti kart) {
        musteri.krediKartiSil(kart);
    }
}
