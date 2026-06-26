/**
 * Class PersonalTrainer
 * Extends Orang, implements BisaMelatih
 * Atribut: noSertifikat (10 digit), spesialisasi, tarif.
 */
public class PersonalTrainer extends Orang implements BisaMelatih {

    private String noSertifikat; // wajib 10 digit
    private String spesialisasi;
    private double tarif;

    public PersonalTrainer(String nama, String noSertifikat, String spesialisasi, double tarif) throws ValidasiException {
        super(nama);
        validasiNoSertifikat(noSertifikat);
        this.noSertifikat = noSertifikat;
        this.spesialisasi = spesialisasi;
        this.tarif = tarif;
    }

    private void validasiNoSertifikat(String noSertifikat) throws ValidasiException {
        if (noSertifikat == null || !noSertifikat.matches("\\d{10}")) {
            throw new ValidasiException("NoSertifikat harus berupa 10 digit angka! Input: " + noSertifikat);
        }
    }

    @Override
    public void latih(String namaMember) {
        System.out.println(nama + " sedang melatih " + namaMember + " (spesialisasi: " + spesialisasi + ")");
    }

    @Override
    public void evaluasi(String namaMember) {
        System.out.println(nama + " melakukan evaluasi progres untuk " + namaMember);
    }

    @Override
    public void buatProgram(String namaMember) {
        System.out.println(nama + " membuat program latihan khusus untuk " + namaMember);
    }

    public String getNoSertifikat() {
        return noSertifikat;
    }

    public void setNoSertifikat(String noSertifikat) throws ValidasiException {
        validasiNoSertifikat(noSertifikat);
        this.noSertifikat = noSertifikat;
    }

    public String getSpesialisasi() {
        return spesialisasi;
    }

    public void setSpesialisasi(String spesialisasi) {
        this.spesialisasi = spesialisasi;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "PersonalTrainer{nama='" + nama + "', noSertifikat='" + noSertifikat +
                "', spesialisasi='" + spesialisasi + "', tarif=" + tarif + "}";
    }
}
