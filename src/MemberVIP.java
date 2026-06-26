import java.util.ArrayList;
import java.util.List;

/**
 * Class MemberVIP
 * Extends MemberGym, implements BisaAksesAlat DAN BisaPersonalTrainer
 * (multiple interface implementation).
 * Member VIP: durasi 3 bulan, akses kelas, bisa booking personal trainer.
 */
public class MemberVIP extends MemberGym implements BisaAksesAlat, BisaPersonalTrainer {

    private int durasiBulan;       // durasi 3 bulan
    private boolean aksesKelas;    // akses kelas khusus VIP
    private int kuotaLatihan;
    private PersonalTrainer trainerTerpilih;
    private List<String> daftarJadwalSesi;

    public MemberVIP(String nama, String noMember, String tglDaftar, String paket) throws ValidasiException {
        super(nama, noMember, tglDaftar, paket);
        this.durasiBulan = 3;
        this.aksesKelas = true;
        this.kuotaLatihan = 30; // kuota lebih besar dari reguler
        this.daftarJadwalSesi = new ArrayList<>();
    }

    @Override
    public void cekAlat() {
        System.out.println("[MemberVIP] " + nama + " mengakses seluruh alat gym + kelas khusus.");
    }

    @Override
    public int getKuotaLatihan() {
        return kuotaLatihan;
    }

    @Override
    public void pilihTrainer(PersonalTrainer trainer) {
        this.trainerTerpilih = trainer;
        System.out.println(nama + " memilih trainer: " + trainer.getNama());
    }

    @Override
    public void jadwalSesi(String tanggal, String jam) {
        if (trainerTerpilih == null) {
            System.out.println("Pilih trainer terlebih dahulu sebelum membuat jadwal sesi!");
            return;
        }
        String sesi = tanggal + " jam " + jam + " bersama " + trainerTerpilih.getNama();
        daftarJadwalSesi.add(sesi);
        System.out.println("Sesi berhasil dijadwalkan: " + sesi);
    }

    public PersonalTrainer getTrainerTerpilih() {
        return trainerTerpilih;
    }

    public List<String> getJadwalSesi() {
        return daftarJadwalSesi;
    }

    public boolean isAksesKelas() {
        return aksesKelas;
    }

    @Override
    public double hitungBiaya() {
        double total = biayaPaket();
        if (trainerTerpilih != null) {
            total += trainerTerpilih.getTarif();
        }
        return total;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("===== INFO MEMBER VIP =====");
        System.out.println("Nama         : " + nama);
        System.out.println("No Member    : " + noMember);
        System.out.println("Tgl Daftar   : " + tglDaftar);
        System.out.println("Paket        : " + paket);
        System.out.println("Durasi       : " + durasiBulan + " bulan");
        System.out.println("Akses Kelas  : " + (aksesKelas ? "Ya" : "Tidak"));
        System.out.println("Kuota Latihan: " + kuotaLatihan + " sesi");
        System.out.println("Trainer      : " + (trainerTerpilih != null ? trainerTerpilih.getNama() : "- belum pilih -"));
        System.out.println("Jadwal Sesi  : " + (daftarJadwalSesi.isEmpty() ? "-" : daftarJadwalSesi));
        System.out.printf("Biaya        : Rp%,.0f%n", hitungBiaya());
    }
}
