/**
 * Class SesiLatihan (composition)
 * Menggabungkan/mengelola objek MemberGym dan PersonalTrainer
 * dalam satu sesi latihan dengan tanggal dan durasi tertentu.
 */
public class SesiLatihan {

    private MemberGym member;          // composition: SesiLatihan "memiliki" MemberGym
    private PersonalTrainer trainer;   // composition: SesiLatihan "memiliki" PersonalTrainer (boleh null kalau reguler tanpa trainer)
    private String tanggal;
    private int durasiMenit;

    public SesiLatihan(MemberGym member, PersonalTrainer trainer, String tanggal, int durasiMenit) {
        this.member = member;
        this.trainer = trainer;
        this.tanggal = tanggal;
        this.durasiMenit = durasiMenit;
    }

    public MemberGym getMember() {
        return member;
    }

    public PersonalTrainer getTrainer() {
        return trainer;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public void tampilkanSesi() {
        System.out.println("----- Sesi Latihan -----");
        System.out.println("Member  : " + member.getNama() + " (" + member.getNoMember() + ")");
        System.out.println("Trainer : " + (trainer != null ? trainer.getNama() : "Mandiri (tanpa trainer)"));
        System.out.println("Tanggal : " + tanggal);
        System.out.println("Durasi  : " + durasiMenit + " menit");
    }

    @Override
    public String toString() {
        return tanggal + " | " + member.getNama() + " | " +
                (trainer != null ? trainer.getNama() : "Mandiri") + " | " + durasiMenit + " menit";
    }
}
