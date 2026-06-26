/**
 * Abstract class MemberGym
 * Parent class untuk MemberReguler dan MemberVIP.
 * Berisi atribut dan method umum yang dimiliki semua member gym.
 */
public abstract class MemberGym {

    protected String nama;
    protected String noMember;     // wajib 8 digit
    protected String tglDaftar;
    protected String paket;        // Silver / Gold / Platinum

    public MemberGym(String nama, String noMember, String tglDaftar, String paket) throws ValidasiException {
        validasiNoMember(noMember);
        validasiPaket(paket);

        this.nama = nama;
        this.noMember = noMember;
        this.tglDaftar = tglDaftar;
        this.paket = paket;
    }

    // ===================== VALIDASI =====================

    protected void validasiNoMember(String noMember) throws ValidasiException {
        if (noMember == null || !noMember.matches("\\d{8}")) {
            throw new ValidasiException("NoMember harus berupa 8 digit angka! Input: " + noMember);
        }
    }

    protected void validasiPaket(String paket) throws ValidasiException {
        if (paket == null ||
                !(paket.equalsIgnoreCase("Silver")
                        || paket.equalsIgnoreCase("Gold")
                        || paket.equalsIgnoreCase("Platinum"))) {
            throw new ValidasiException("Paket tidak valid! Harus Silver/Gold/Platinum. Input: " + paket);
        }
    }

    // ===================== METHOD ABSTRAK =====================

    /** Menghitung biaya member berdasarkan paket (dan tambahan layanan lain jika ada) */
    public abstract double hitungBiaya();

    /** Menampilkan informasi lengkap member (akan di-override tiap child) */
    public abstract void tampilkanInfo();

    // ===================== GETTER & SETTER =====================

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoMember() {
        return noMember;
    }

    public void setNoMember(String noMember) throws ValidasiException {
        validasiNoMember(noMember);
        this.noMember = noMember;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String tglDaftar) {
        this.tglDaftar = tglDaftar;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) throws ValidasiException {
        validasiPaket(paket);
        this.paket = paket;
    }

    /** Biaya dasar paket, dipakai oleh child class saat hitungBiaya() */
    protected double biayaPaket() {
        switch (paket.toLowerCase()) {
            case "silver":
                return 200000;
            case "gold":
                return 350000;
            case "platinum":
                return 500000;
            default:
                return 0;
        }
    }
}
