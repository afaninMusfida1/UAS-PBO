import java.util.Scanner;

/**
 * Class Main
 * Menu interaktif (looping menggunakan Scanner) untuk Sistem Gym/Fitness.
 * Semua input pengguna dibungkus try-catch untuk menangani ValidasiException
 * maupun input yang salah format (NumberFormatException).
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static Gym gym = new Gym("Gym Sehat Bersama");

    public static void main(String[] args) {
        seedDataAwal(); // beberapa data contoh agar mudah didemokan
        boolean jalan = true;

        while (jalan) {
            tampilkanMenu();
            String pilihan = sc.nextLine().trim();

            try {
                switch (pilihan) {
                    case "1":
                        tambahMember();
                        break;
                    case "2":
                        gym.lihatSemuaMember();
                        break;
                    case "3":
                        cariMember();
                        break;
                    case "4":
                        updatePaketMember();
                        break;
                    case "5":
                        hapusMember();
                        break;
                    case "6":
                        tambahTrainer();
                        break;
                    case "7":
                        gym.lihatSemuaTrainer();
                        break;
                    case "8":
                        bookingSesiTrainer();
                        break;
                    case "9":
                        sesiLatihanMandiri();
                        break;
                    case "10":
                        gym.lihatSemuaSesi();
                        break;
                    case "11":
                        hitungBiaya();
                        break;
                    case "12":
                        gym.laporanLengkap();
                        break;
                    case "0":
                        jalan = false;
                        System.out.println("Terima kasih telah menggunakan Sistem Gym!");
                        break;
                    default:
                        System.out.println(">> Pilihan tidak valid, silakan coba lagi.");
                }
            } catch (ValidasiException e) {
                // exception khusus validasi data
                System.out.println(">> [Validasi Gagal] " + e.getMessage());
            } catch (NumberFormatException e) {
                // jika input angka tidak sesuai format
                System.out.println(">> [Input Error] Masukkan angka yang valid!");
            } catch (Exception e) {
                // pengaman tambahan untuk error tak terduga
                System.out.println(">> [Error] Terjadi kesalahan: " + e.getMessage());
            }

            System.out.println();
        }

        sc.close();
    }

    static void tampilkanMenu() {
        System.out.println("=========================================");
        System.out.println("      SISTEM GYM / FITNESS - " + gym.getNamaGym());
        System.out.println("=========================================");
        System.out.println(" 1. Tambah Member (Reguler/VIP)");
        System.out.println(" 2. Lihat Semua Member");
        System.out.println(" 3. Cari Member");
        System.out.println(" 4. Update Paket Member");
        System.out.println(" 5. Hapus Member");
        System.out.println(" 6. Tambah Personal Trainer");
        System.out.println(" 7. Lihat Semua Trainer");
        System.out.println(" 8. Booking Sesi dengan Personal Trainer (VIP)");
        System.out.println(" 9. Catat Sesi Latihan Mandiri");
        System.out.println("10. Lihat Semua Sesi Latihan");
        System.out.println("11. Hitung Biaya Member");
        System.out.println("12. Laporan (Member Aktif & Total Pendapatan)");
        System.out.println(" 0. Keluar");
        System.out.print("Pilih menu: ");
    }

    // ===================== AKSI MENU =====================

    static void tambahMember() throws ValidasiException {
        System.out.print("Tipe Member (1=Reguler, 2=VIP): ");
        String tipe = sc.nextLine().trim();

        System.out.print("Nama          : ");
        String nama = sc.nextLine().trim();
        System.out.print("No Member (8 digit): ");
        String noMember = sc.nextLine().trim();
        System.out.print("Tanggal Daftar (dd-mm-yyyy): ");
        String tglDaftar = sc.nextLine().trim();
        System.out.print("Paket (Silver/Gold/Platinum): ");
        String paket = sc.nextLine().trim();

        if (tipe.equals("1")) {
            MemberReguler m = new MemberReguler(nama, noMember, tglDaftar, paket);
            gym.tambahMember(m);
        } else if (tipe.equals("2")) {
            MemberVIP m = new MemberVIP(nama, noMember, tglDaftar, paket);
            gym.tambahMember(m);
        } else {
            System.out.println(">> Tipe member tidak valid (harus 1 atau 2).");
        }
    }

    static void cariMember() {
        System.out.print("Masukkan NoMember yang dicari: ");
        String no = sc.nextLine().trim();
        MemberGym m = gym.cariMember(no);
        if (m != null) {
            m.tampilkanInfo();
        } else {
            System.out.println(">> Member tidak ditemukan.");
        }
    }

    static void updatePaketMember() throws ValidasiException {
        System.out.print("Masukkan NoMember: ");
        String no = sc.nextLine().trim();
        System.out.print("Paket baru (Silver/Gold/Platinum): ");
        String paketBaru = sc.nextLine().trim();
        gym.updatePaketMember(no, paketBaru);
    }

    static void hapusMember() {
        System.out.print("Masukkan NoMember yang akan dihapus: ");
        String no = sc.nextLine().trim();
        gym.hapusMember(no);
    }

    static void tambahTrainer() throws ValidasiException {
        System.out.print("Nama Trainer        : ");
        String nama = sc.nextLine().trim();
        System.out.print("No Sertifikat (10 digit): ");
        String noSertifikat = sc.nextLine().trim();
        System.out.print("Spesialisasi        : ");
        String spesialisasi = sc.nextLine().trim();
        System.out.print("Tarif per sesi (contoh 150000): ");
        double tarif = Double.parseDouble(sc.nextLine().trim());

        PersonalTrainer trainer = new PersonalTrainer(nama, noSertifikat, spesialisasi, tarif);
        gym.tambahTrainer(trainer);
    }

    static void bookingSesiTrainer() throws ValidasiException {
        System.out.print("NoMember (VIP)       : ");
        String noMember = sc.nextLine().trim();
        System.out.print("No Sertifikat Trainer: ");
        String noSertifikat = sc.nextLine().trim();
        System.out.print("Tanggal sesi (dd-mm-yyyy): ");
        String tanggal = sc.nextLine().trim();
        System.out.print("Jam sesi (hh:mm)     : ");
        String jam = sc.nextLine().trim();

        gym.bookingSesiTrainer(noMember, noSertifikat, tanggal, jam);
    }

    static void sesiLatihanMandiri() throws ValidasiException {
        System.out.print("NoMember           : ");
        String noMember = sc.nextLine().trim();
        System.out.print("Tanggal (dd-mm-yyyy): ");
        String tanggal = sc.nextLine().trim();
        System.out.print("Durasi (menit)      : ");
        int durasi = Integer.parseInt(sc.nextLine().trim());

        gym.sesiLatihanMandiri(noMember, tanggal, durasi);
    }

    static void hitungBiaya() throws ValidasiException {
        System.out.print("Masukkan NoMember: ");
        String no = sc.nextLine().trim();
        double biaya = gym.hitungBiayaMember(no);
        System.out.printf(">> Total biaya untuk member ini: Rp%,.0f%n", biaya);
    }

    /** Data contoh awal agar aplikasi langsung bisa didemokan tanpa input manual */
    static void seedDataAwal() {
        try {
            gym.tambahMember(new MemberReguler("Andi Saputra", "10000001", "01-01-2026", "Silver"));
            gym.tambahMember(new MemberVIP("Budi Santoso", "10000002", "05-01-2026", "Platinum"));
            gym.tambahTrainer(new PersonalTrainer("Coach Rian", "1000000001", "Body Building", 150000));
            gym.tambahTrainer(new PersonalTrainer("Coach Sinta", "1000000002", "Yoga & Flexibility", 120000));
        } catch (ValidasiException e) {
            System.out.println("Gagal membuat data awal: " + e.getMessage());
        }
    }
}
