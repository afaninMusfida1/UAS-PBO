/**
 * Class Orang
 * Parent class sederhana untuk PersonalTrainer (memenuhi requirement
 * "Class: PersonalTrainer extends Orang").
 */
public class Orang {

    protected String nama;

    public Orang(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
