/**
 * Class MemberReguler
 * Extends MemberGym, implements BisaAksesAlat
 * Member reguler hanya bisa akses alat gym (durasi 1 bulan), tidak bisa personal trainer.
 */
public class MemberReguler extends MemberGym implements BisaAksesAlat {

    private int durasiBulan; // durasi 1 bulan
    private int kuotaLatihan; // sisa kuota sesi latihan per bulan

    public MemberReguler(String nama, String noMember, String tglDaftar, String paket) throws ValidasiException {
        super(nama, noMember, tglDaftar, paket);
        this.durasiBulan = 1;
        this.kuotaLatihan = 12; // contoh: 12 sesi per bulan
    }

    @Override
    public void cekAlat() {
        System.out.println("[MemberReguler] " + nama + " mengakses alat gym standar.");
    }

    @Override
    public int getKuotaLatihan() {
        return kuotaLatihan;
    }

    public void pakaiKuota() {
        if (kuotaLatihan > 0) {
            kuotaLatihan--;
        } else {
            System.out.println("Kuota latihan bulan ini sudah habis!");
        }
    }

    @Override
    public double hitungBiaya() {
        // Reguler hanya membayar biaya paket dasar
        return biayaPaket();
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("===== INFO MEMBER REGULER =====");
        System.out.println("Nama         : " + nama);
        System.out.println("No Member    : " + noMember);
        System.out.println("Tgl Daftar   : " + tglDaftar);
        System.out.println("Paket        : " + paket);
        System.out.println("Durasi       : " + durasiBulan + " bulan");
        System.out.println("Kuota Latihan: " + kuotaLatihan + " sesi");
        System.out.printf("Biaya        : Rp%,.0f%n", hitungBiaya());
    }
}
