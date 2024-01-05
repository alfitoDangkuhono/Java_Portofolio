package com.alfitoDangkuhono.myPortofolio.Model;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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
    @CreationTimestamp
    @Column(name = "created_at")
    public Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;


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
    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
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
