/**
 * Interface BisaPersonalTrainer
 * Diimplementasikan oleh MemberVIP karena hanya member VIP
 * yang berhak booking sesi dengan personal trainer.
 */
public interface BisaPersonalTrainer {

    void pilihTrainer(PersonalTrainer trainer);

    void jadwalSesi(String tanggal, String jam);
}
