/**
 * Interface BisaMelatih
 * Diimplementasikan oleh PersonalTrainer karena trainer
 * memiliki kemampuan untuk melatih, mengevaluasi, dan membuat program.
 */
public interface BisaMelatih {

    void latih(String namaMember);

    void evaluasi(String namaMember);

    void buatProgram(String namaMember);
}
