/**
 * Custom Exception untuk menangani kesalahan validasi input
 * pada Sistem Gym/Fitness.
 * Digunakan untuk validasi NoMember (8 digit), NoSertifikat (10 digit),
 * dan Paket (harus Silver/Gold/Platinum).
 */
public class ValidasiException extends Exception {

    public ValidasiException(String message) {
        super(message);
    }
}
