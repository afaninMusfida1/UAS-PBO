import java.util.ArrayList;
import java.util.List;

/**
 * Class Gym (manager)
 * Mengelola seluruh data: member, trainer, sesi latihan.
 * Menyediakan fitur CRUD member, booking sesi, hitung biaya, dan laporan.
 */
public class Gym {

    private String namaGym;
    private List<MemberGym> daftarMember;
    private List<PersonalTrainer> daftarTrainer;
    private List<SesiLatihan> daftarSesi;

    public Gym(String namaGym) {
        this.namaGym = namaGym;
        this.daftarMember = new ArrayList<>();
        this.daftarTrainer = new ArrayList<>();
        this.daftarSesi = new ArrayList<>();
    }

    // ===================== CRUD MEMBER =====================

    /** CREATE - Tambah member baru. Mengecek duplikasi noMember. */
    public void tambahMember(MemberGym member) throws ValidasiException {
        if (cariMember(member.getNoMember()) != null) {
            throw new ValidasiException("NoMember " + member.getNoMember() + " sudah terdaftar!");
        }
        daftarMember.add(member);
        System.out.println(">> Member " + member.getNama() + " berhasil ditambahkan.");
    }

    /** READ - Lihat semua member */
    public void lihatSemuaMember() {
        if (daftarMember.isEmpty()) {
            System.out.println("Belum ada member terdaftar.");
            return;
        }
        for (MemberGym m : daftarMember) {
            m.tampilkanInfo();
            System.out.println();
        }
    }

    /** READ - Cari member berdasarkan noMember */
    public MemberGym cariMember(String noMember) {
        for (MemberGym m : daftarMember) {
            if (m.getNoMember().equals(noMember)) {
                return m;
            }
        }
        return null;
    }

    /** UPDATE - Update paket member */
    public void updatePaketMember(String noMember, String paketBaru) throws ValidasiException {
        MemberGym m = cariMember(noMember);
        if (m == null) {
            throw new ValidasiException("Member dengan NoMember " + noMember + " tidak ditemukan!");
        }
        m.setPaket(paketBaru);
        System.out.println(">> Paket member " + m.getNama() + " berhasil diubah menjadi " + paketBaru);
    }

    /** DELETE - Hapus member berdasarkan noMember */
    public boolean hapusMember(String noMember) {
        MemberGym m = cariMember(noMember);
        if (m != null) {
            daftarMember.remove(m);
            System.out.println(">> Member " + m.getNama() + " berhasil dihapus.");
            return true;
        }
        System.out.println(">> Member tidak ditemukan.");
        return false;
    }

    // ===================== TRAINER =====================

    public void tambahTrainer(PersonalTrainer trainer) {
        daftarTrainer.add(trainer);
        System.out.println(">> Trainer " + trainer.getNama() + " berhasil ditambahkan.");
    }

    public void lihatSemuaTrainer() {
        if (daftarTrainer.isEmpty()) {
            System.out.println("Belum ada trainer terdaftar.");
            return;
        }
        for (PersonalTrainer t : daftarTrainer) {
            System.out.println("- " + t);
        }
    }

    public PersonalTrainer cariTrainer(String noSertifikat) {
        for (PersonalTrainer t : daftarTrainer) {
            if (t.getNoSertifikat().equals(noSertifikat)) {
                return t;
            }
        }
        return null;
    }

    // ===================== FITUR: BOOKING SESI =====================

    /** Booking sesi dengan personal trainer (khusus MemberVIP) */
    public void bookingSesiTrainer(String noMember, String noSertifikat, String tanggal, String jam) throws ValidasiException {
        MemberGym m = cariMember(noMember);
        if (m == null) {
            throw new ValidasiException("Member tidak ditemukan!");
        }
        if (!(m instanceof MemberVIP)) {
            throw new ValidasiException("Hanya MemberVIP yang bisa booking personal trainer!");
        }
        PersonalTrainer trainer = cariTrainer(noSertifikat);
        if (trainer == null) {
            throw new ValidasiException("Trainer tidak ditemukan!");
        }

        MemberVIP vip = (MemberVIP) m;
        vip.pilihTrainer(trainer);
        vip.jadwalSesi(tanggal, jam);

        SesiLatihan sesi = new SesiLatihan(vip, trainer, tanggal, 60);
        daftarSesi.add(sesi);

        trainer.latih(vip.getNama());
        trainer.buatProgram(vip.getNama());
    }

    /** Sesi latihan mandiri (reguler ataupun VIP tanpa trainer) */
    public void sesiLatihanMandiri(String noMember, String tanggal, int durasiMenit) throws ValidasiException {
        MemberGym m = cariMember(noMember);
        if (m == null) {
            throw new ValidasiException("Member tidak ditemukan!");
        }
        if (m instanceof BisaAksesAlat) {
            ((BisaAksesAlat) m).cekAlat();
        }
        SesiLatihan sesi = new SesiLatihan(m, null, tanggal, durasiMenit);
        daftarSesi.add(sesi);
        System.out.println(">> Sesi latihan mandiri untuk " + m.getNama() + " berhasil dicatat.");
    }

    public void lihatSemuaSesi() {
        if (daftarSesi.isEmpty()) {
            System.out.println("Belum ada sesi latihan tercatat.");
            return;
        }
        for (SesiLatihan s : daftarSesi) {
            s.tampilkanSesi();
            System.out.println();
        }
    }

    // ===================== FITUR: HITUNG BIAYA =====================

    public double hitungBiayaMember(String noMember) throws ValidasiException {
        MemberGym m = cariMember(noMember);
        if (m == null) {
            throw new ValidasiException("Member tidak ditemukan!");
        }
        return m.hitungBiaya();
    }

    // ===================== LAPORAN =====================

    public void laporanMemberAktif() {
        System.out.println("===== LAPORAN MEMBER AKTIF =====");
        int reguler = 0, vip = 0;
        for (MemberGym m : daftarMember) {
            if (m instanceof MemberReguler) reguler++;
            if (m instanceof MemberVIP) vip++;
        }
        System.out.println("Total Member Reguler : " + reguler);
        System.out.println("Total Member VIP     : " + vip);
        System.out.println("Total Member Aktif   : " + daftarMember.size());
    }

    public double totalPendapatan() {
        double total = 0;
        for (MemberGym m : daftarMember) {
            total += m.hitungBiaya();
        }
        return total;
    }

    public void laporanLengkap() {
        laporanMemberAktif();
        System.out.printf("Total Pendapatan     : Rp%,.0f%n", totalPendapatan());
        System.out.println("Total Sesi Latihan   : " + daftarSesi.size());
        System.out.println("Total Trainer        : " + daftarTrainer.size());
    }

    public String getNamaGym() {
        return namaGym;
    }

    public List<MemberGym> getDaftarMember() {
        return daftarMember;
    }

    public List<PersonalTrainer> getDaftarTrainer() {
        return daftarTrainer;
    }
}
