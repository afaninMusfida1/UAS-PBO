# Sistem Gym / Fitness — UAS Pemrograman Berorientasi Objek (Java)

Studi Kasus 8: **Sistem Gym / Fitness**

## Cara Menjalankan (TANPA IDE / TANPA terminal IDE)

Pastikan sudah install **JDK** (Java Development Kit), bukan hanya JRE.
Cek dengan: `javac -version`

```bash
# 1. Masuk ke folder src
cd src

# 2. Compile semua file java
javac *.java

# 3. Jalankan program
java Main
```

Jika menggunakan IDE (IntelliJ / Eclipse / NetBeans / VS Code):
- Buka folder `SistemGym` sebagai project
- Set `src` sebagai source folder
- Run file `Main.java`

## Struktur Class (sesuai requirement soal)

| Komponen | Implementasi |
|---|---|
| Abstract class | `MemberGym` |
| Child class (inheritance) | `MemberReguler`, `MemberVIP` (keduanya extends `MemberGym`) |
| Interface (3 buah, min 2) | `BisaAksesAlat`, `BisaPersonalTrainer`, `BisaMelatih` |
| Multiple interface | `MemberVIP implements BisaAksesAlat, BisaPersonalTrainer` |
| Class tambahan (extends) | `PersonalTrainer extends Orang implements BisaMelatih` |
| Class composition (manager) | `Gym` — mengelola List\<MemberGym\>, List\<PersonalTrainer\>, List\<SesiLatihan\> |
| Class composition (data gabungan) | `SesiLatihan` — menggabungkan MemberGym + PersonalTrainer + tanggal + durasi |
| Custom exception | `ValidasiException extends Exception` |
| Validasi | NoMember 8 digit, NoSertifikat 10 digit, Paket harus Silver/Gold/Platinum |
| Menu interaktif | `Main.java` — looping `while` + `Scanner`, dibungkus `try-catch` |

## Daftar File

```
SistemGym/
└── src/
    ├── ValidasiException.java     (custom exception)
    ├── BisaAksesAlat.java         (interface 1)
    ├── BisaPersonalTrainer.java   (interface 2)
    ├── BisaMelatih.java           (interface 3)
    ├── MemberGym.java             (abstract class / parent)
    ├── MemberReguler.java         (child class 1)
    ├── MemberVIP.java             (child class 2, multiple interface)
    ├── Orang.java                 (parent untuk PersonalTrainer)
    ├── PersonalTrainer.java       (extends Orang, implements BisaMelatih)
    ├── SesiLatihan.java           (composition: member + trainer + tanggal + durasi)
    ├── Gym.java                   (manager / composition utama — CRUD & Laporan)
    └── Main.java                  (menu interaktif Scanner)
```

## Fitur yang sudah jalan

1. **Daftar member** (Reguler / VIP) — menu 1
2. **Lihat / cari / update / hapus member** — menu 2–5 (CRUD)
3. **Tambah & lihat trainer** — menu 6–7
4. **Booking sesi dengan personal trainer** (khusus VIP) — menu 8
5. **Catat sesi latihan mandiri** (Reguler maupun VIP) — menu 9
6. **Lihat semua sesi latihan** — menu 10
7. **Hitung biaya** (paket + biaya sesi trainer jika ada) — menu 11
8. **Laporan**: jumlah member aktif (reguler/VIP) & total pendapatan — menu 12

Saat program dijalankan, sudah otomatis terisi 2 contoh member dan 2 contoh trainer
(`seedDataAwal()` di `Main.java`) supaya bisa langsung didemokan. Anda tetap bisa
menambah/menghapus data sendiri lewat menu.

## Validasi yang diterapkan (custom exception `ValidasiException`)

- **NoMember**: harus tepat 8 digit angka (`MemberGym.validasiNoMember`)
- **NoSertifikat**: harus tepat 10 digit angka (`PersonalTrainer.validasiNoSertifikat`)
- **Paket**: harus salah satu dari `Silver` / `Gold` / `Platinum` (`MemberGym.validasiPaket`)
- Duplikasi NoMember saat tambah member baru
- Member/trainer tidak ditemukan saat update/hapus/booking
- Hanya `MemberVIP` yang boleh booking personal trainer (dicek dengan `instanceof`)

Semua validasi dilempar sebagai `ValidasiException` dan ditangkap dengan
`try-catch` di `Main.java`, sehingga program **tidak akan crash** meskipun
input pengguna salah (contoh: NoMember cuma 5 digit, paket "Diamond", dll).

## Poin OOP 

- **Abstraction**: `MemberGym` abstract class, method `hitungBiaya()` dan
  `tampilkanInfo()` abstrak — wajib di-override oleh child class.
- **Inheritance**: `MemberReguler` & `MemberVIP` extends `MemberGym`;
  `PersonalTrainer` extends `Orang`.
- **Polymorphism**: `Gym.lihatSemuaMember()` memanggil `m.tampilkanInfo()`
  lewat referensi `MemberGym`, tapi hasil berbeda untuk Reguler vs VIP
  (dynamic method dispatch). Begitu juga `hitungBiaya()`.
- **Interface & multiple interface**: `MemberVIP` implements 2 interface
  sekaligus (`BisaAksesAlat`, `BisaPersonalTrainer`).
- **Encapsulation**: semua field `private`/`protected` + getter-setter,
  beberapa setter punya validasi (misal `setNoMember`, `setPaket`).
- **Composition**: `Gym` "memiliki" banyak `MemberGym`, `PersonalTrainer`,
  dan `SesiLatihan`; `SesiLatihan` "memiliki" 1 `MemberGym` dan 1
  `PersonalTrainer` (bukan inheritance, tapi has-a relationship).
- **Exception handling**: custom checked exception `ValidasiException`,
  dilempar dengan `throw`, ditangkap dengan `try-catch` di banyak level.

