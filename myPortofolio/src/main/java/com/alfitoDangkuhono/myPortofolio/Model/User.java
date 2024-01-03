package com.alfitoDangkuhono.myPortofolio.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    @Column(name ="name")
    public String name;
    @Column(name = "usia")
    public Integer usia;
    @Column(name = "jenisKelamin")
    public String jenisKelamin;

    public User (){}

    public User(int id, String name, Integer usia, String jenisKelamin) {
        this.id = id;
        this.name = name;
        this.usia = usia;
        this.jenisKelamin = jenisKelamin;
    }
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsia() {
        return usia;
    }
    public void setUsia(int usia) {
        this.usia = usia;
    }
    public String getJenisKelamin() {
        return jenisKelamin;
    }
    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
