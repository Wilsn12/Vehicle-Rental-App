package com.example.demospringboot.entity;
import jakarta.persistence.Entity;

@Entity
public class Motor extends Kendaraan {
    private String tipe;
    private int roda;

    // constructor
    public Motor() {}

    public Motor (String plat, String model, String merk, int tahun, String status, String BBM, double harga, String warna, String tip, int rd, Long id) {
        super(plat, model, merk, tahun, status, BBM, harga, warna, id);
        this.tipe = tip;
        this.roda = rd;
    }

    // setter getter
    public void setTipe(String tip) {
        this.tipe = tip;
    }

    public String getTipe() {
        return tipe;
    }

    public void setRoda(int rd) {
        this.roda = rd;
    }

    public int getRoda() {
        return roda;
    }

    @Override
    public double hitungBiaya(int lamaSewa) {
        return getHarga() * lamaSewa;
    }  
}
