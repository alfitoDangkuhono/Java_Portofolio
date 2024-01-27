package Application.test.simpleCRUD.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "table_kendaraan")
public class DataKendaraan {

    @Id
    @Column(name = "No_Registrasi")
    private String NoRegistrasi;
    @Column(name = "nama_pemilik",nullable = false)
    public String name_pemilik;
    @Column(name = "alamat",nullable = false)
    private String alamat;
    @Column(name = "merk",nullable = false)
    private String merk;
    @Column(name = "tahun_pembuatan",nullable = false,length = 4)
    private Integer tahun_pembuatan;
    @Column(name = "kapasitas_silinder",nullable = false)
    private Integer kapasitas_silinder;
    @Column(name = "bahan_bakar",nullable = false)
    private String bahan_bakar;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "warna_id")
    private WarnaKendaraan warnaKendaraan;

    public DataKendaraan(String noRegistrasi, String name_pemilik, String alamat, String merk, Integer tahun_pembuatan, Integer kapasitas_silinder, String bahan_bakar, WarnaKendaraan warnaKendaraan) {
        NoRegistrasi = noRegistrasi;
        this.name_pemilik = name_pemilik;
        this.alamat = alamat;
        this.merk = merk;
        this.tahun_pembuatan = tahun_pembuatan;
        this.kapasitas_silinder = kapasitas_silinder;
        this.bahan_bakar = bahan_bakar;
        this.warnaKendaraan = warnaKendaraan;
    }

    public DataKendaraan() {
    }
    public String getNoRegistrasi() {
        return NoRegistrasi;
    }

    public void setNoRegistrasi(String noRegistrasi) {
        NoRegistrasi = noRegistrasi;
    }

    public String getName_pemilik() {
        return name_pemilik;
    }

    public void setName_pemilik(String name_pemilik) {
        this.name_pemilik = name_pemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public Integer getTahun_pembuatan() {
        return tahun_pembuatan;
    }

    public void setTahun_pembuatan(Integer tahun_pembuatan) {
        this.tahun_pembuatan = tahun_pembuatan;
    }

    public Integer getKapasitas_silinder() {
        return kapasitas_silinder;
    }

    public void setKapasitas_silinder(Integer kapasitas_silinder) {
        this.kapasitas_silinder = kapasitas_silinder;
    }

    public String getBahan_bakar() {
        return bahan_bakar;
    }

    public void setBahan_bakar(String bahan_bakar) {
        this.bahan_bakar = bahan_bakar;
    }
        public WarnaKendaraan getWarnaKendaraan() {
        return warnaKendaraan;
    }

    public void setWarnaKendaraan(WarnaKendaraan warnaKendaraan) {
        this.warnaKendaraan = warnaKendaraan;
    }

    @Override
    public String toString() {
        return "DataKendaraan{" +
                "NoRegistrasi='" + NoRegistrasi + '\'' +
                ", name_pemilik='" + name_pemilik + '\'' +
                ", alamat='" + alamat + '\'' +
                ", merk='" + merk + '\'' +
                ", tahun_pembuatan=" + tahun_pembuatan +
                ", kapasitas_silinder=" + kapasitas_silinder +
                ", bahan_bakar='" + bahan_bakar + '\'' +
                ", warnaKendaraan=" + warnaKendaraan +
                '}';
    }
}
