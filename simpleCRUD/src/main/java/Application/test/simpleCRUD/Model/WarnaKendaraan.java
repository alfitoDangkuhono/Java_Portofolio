package Application.test.simpleCRUD.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "table_warna")
public class WarnaKendaraan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_warna")
    public Integer Id;
    @Column(name = "nama_warna")
    public String nama_warna;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "warnaKendaraan",orphanRemoval = true)
    private List<DataKendaraan>dataKendaraan;
    public WarnaKendaraan() {
    }

    public WarnaKendaraan(Integer id, String nama_warna) {
        Id = id;
        this.nama_warna = nama_warna;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getWarna_kendaraan() {
        return nama_warna;
    }

    public void setWarna_kendaraan(String nama_warna) {
        this.nama_warna = nama_warna;
    }
}
