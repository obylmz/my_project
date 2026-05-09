package com.bank.app.main;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;
import com.bank.app.people.BankaPersoneli;
import com.bank.app.people.Musteri;
import com.bank.app.service.BankaService;

/**
 * Main sınıfı - Uygulamanın başlangıç noktasıdır.
 * Geliştirilen tüm yapıların çalıştığını gösteren örnek senaryoları içerir.
 */
public class Main {

    public static void main(String[] args) {

        // BankaService nesnesi oluşturuluyor - tüm banka işlemleri bu servis üzerinden yürütülecek
        BankaService bankaService = new BankaService();

        System.out.println("=============================================================");
        System.out.println("        BANKA YÖNETİM SİSTEMİ - ÖRNEK SENARYO");
        System.out.println("=============================================================");

        // ---------------------------------------------------------------
        // 1. BANKA PERSONELİ OLUŞTURMA
        // ---------------------------------------------------------------
        System.out.println("\n--- 1. Banka Personeli Oluşturma ---");
        BankaPersoneli personel = bankaService.personelOlustur(
                "Ahmet", "Yılmaz", "ahmet.yilmaz@banka.com", 551234567);

        // ---------------------------------------------------------------
        // 2. MÜŞTERİ OLUŞTURMA
        // ---------------------------------------------------------------
        System.out.println("\n--- 2. Müşteri Oluşturma ---");
        Musteri musteri1 = bankaService.musteriOlustur(
                "Mehmet", "Demir", "mehmet.demir@email.com", 559876543);

        Musteri musteri2 = bankaService.musteriOlustur(
                "Ayşe", "Kaya", "ayse.kaya@email.com", 554567890);

        // Personelin temsilcisi olduğu müşterileri ekliyoruz
        personel.musteriEkle(musteri1);
        personel.musteriEkle(musteri2);
        System.out.println("Personel Bilgileri: " + personel);

        // ---------------------------------------------------------------
        // 3. MÜŞTERİ ADINA HESAP AÇMA
        // ---------------------------------------------------------------
        System.out.println("\n--- 3. Müşteri Adına Hesap Açma ---");
        // Müşteri 1 için vadesiz hesap açılıyor (5000 TL başlangıç bakiyesi)
        bankaService.hesapAc(musteri1, "vadesiz", 5000);
        // Müşteri 1 için yatırım hesabı açılıyor (10000 TL başlangıç bakiyesi)
        bankaService.hesapAc(musteri1, "yatirim", 10000);
        // Müşteri 2 için vadesiz hesap açılıyor (3000 TL başlangıç bakiyesi)
        bankaService.hesapAc(musteri2, "vadesiz", 3000);

        // Müşteri bilgileri yazdırılıyor
        System.out.println("\nMüşteri 1 Bilgileri: " + musteri1);
        System.out.println("Müşteri 2 Bilgileri: " + musteri2);

        // Hesap detayları yazdırılıyor
        System.out.println("\nMüşteri 1 Hesapları:");
        for (BankaHesabi hesap : musteri1.getHesaplar()) {
            System.out.println("  " + hesap);
        }
        System.out.println("Müşteri 2 Hesapları:");
        for (BankaHesabi hesap : musteri2.getHesaplar()) {
            System.out.println("  " + hesap);
        }

        // ---------------------------------------------------------------
        // 4. HESABA PARA YATIRMA (Yatırım Hesabına)
        // ---------------------------------------------------------------
        System.out.println("\n--- 4. Hesaba Para Yatırma ---");
        // Müşteri 1'in yatırım hesabını alıyoruz (2. açılan hesap, index 1)
        YatirimHesabi yatirimHesabi = (YatirimHesabi) musteri1.getHesaplar().get(1);
        bankaService.yatirimHesabinaParaEkle(yatirimHesabi, 5000);

        // ---------------------------------------------------------------
        // 5. HESAPLAR ARASI PARA TRANSFERİ
        // ---------------------------------------------------------------
        System.out.println("\n--- 5. Hesaplar Arası Para Transferi ---");
        // Müşteri 1'in vadesiz hesabından müşteri 2'nin vadesiz hesabına 1500 TL transfer
        VadesizHesap gonderenHesap = (VadesizHesap) musteri1.getHesaplar().get(0);
        BankaHesabi aliciHesap = musteri2.getHesaplar().get(0);
        bankaService.paraTransferiYap(aliciHesap, gonderenHesap, 1500);

        // ---------------------------------------------------------------
        // 6. MÜŞTERİYE KREDİ KARTI TANIMLAMA
        // ---------------------------------------------------------------
        System.out.println("\n--- 6. Müşteriye Kredi Kartı Tanımlama ---");
        bankaService.krediKartiTanimla(musteri1, 15000);
        // Kredi kartı bilgileri yazdırılıyor
        KrediKarti kart = musteri1.getKrediKartlari().get(0);
        System.out.println("Kart Bilgileri: " + kart);

        // Kart ile harcama simülasyonu - güncel borcu artırıyoruz
        System.out.println("\n--- Kart ile 2000 TL harcama yapılıyor ---");
        kart.setGuncelBorc(2000);
        System.out.println("Güncellenen Kart Bilgileri: " + kart);

        // ---------------------------------------------------------------
        // 7. KREDİ KARTI BORCU ÖDEME
        // ---------------------------------------------------------------
        System.out.println("\n--- 7. Kredi Kartı Borcu Ödeme ---");
        // Müşteri 1'in vadesiz hesabından kredi kartı borcunu ödüyoruz
        bankaService.krediKartiBorcuOde(gonderenHesap, kart, 2000);

        // ---------------------------------------------------------------
        // 8. YATIRIM HESABINDAN PARA ÇEKME
        // ---------------------------------------------------------------
        System.out.println("\n--- 8. Yatırım Hesabından Para Çekme ---");
        bankaService.yatirimHesabindanParaCek(yatirimHesabi, 3000);

        // ---------------------------------------------------------------
        // 9. HESAP SİLME İŞLEMİ
        // ---------------------------------------------------------------
        System.out.println("\n--- 9. Hesap Silme İşlemi ---");
        // Bakiyesi olan hesabı silmeye çalışıyoruz (uyarı vermeli)
        System.out.println("Bakiyesi olan hesap silme denemesi:");
        bankaService.hesapSil(musteri1, gonderenHesap);

        // Bakiyeyi 0'a çekip tekrar silme denemesi
        System.out.println("\nBakiye 0'a çekildikten sonra hesap silme:");
        gonderenHesap.setBakiye(0);
        System.out.println("Hesap bakiyesi 0'a çekildi.");
        bankaService.hesapSil(musteri1, gonderenHesap);

        // ---------------------------------------------------------------
        // 10. KREDİ KARTI SİLME İŞLEMİ
        // ---------------------------------------------------------------
        System.out.println("\n--- 10. Kredi Kartı Silme İşlemi ---");
        // Borcu olmayan kartı siliyoruz (borç 0'a düştü, ödeme yapıldı)
        System.out.println("Borcu olmayan kredi kartı silme:");
        bankaService.krediKartiSil(musteri1, kart);

        // Yeni bir kart oluşturup borçlu kartı silmeye çalışıyoruz
        System.out.println("\nBorcu olan kredi kartı silme denemesi:");
        musteri1.krediKartiEkle(20000);
        KrediKarti yeniKart = musteri1.getKrediKartlari().get(0);
        yeniKart.setGuncelBorc(5000); // 5000 TL borç ekleniyor
        bankaService.krediKartiSil(musteri1, yeniKart);

        // ---------------------------------------------------------------
        // ÖZET BİLGİLER
        // ---------------------------------------------------------------
        System.out.println("\n=============================================================");
        System.out.println("                     ÖZET BİLGİLER");
        System.out.println("=============================================================");
        System.out.println("Personel: " + personel);
        System.out.println("\nMüşteri 1: " + musteri1);
        System.out.println("Hesapları:");
        for (BankaHesabi hesap : musteri1.getHesaplar()) {
            System.out.println("  " + hesap);
        }
        System.out.println("Kredi Kartları:");
        for (KrediKarti k : musteri1.getKrediKartlari()) {
            System.out.println("  " + k);
        }

        System.out.println("\nMüşteri 2: " + musteri2);
        System.out.println("Hesapları:");
        for (BankaHesabi hesap : musteri2.getHesaplar()) {
            System.out.println("  " + hesap);
        }

        System.out.println("\n=============================================================");
        System.out.println("       TÜM İŞLEMLER BAŞARIYLA TAMAMLANDI!");
        System.out.println("=============================================================");
    }
}
