package com.example.demospringboot.entity;
import jakarta.persistence.Entity;

@Entity
public class Bus extends Kendaraan {
    private int jumlah;
    private String fitur;

    // constructor
    public Bus() {}

    public Bus(String plat, String model, String merk, int tahun, String status, String BBM, double harga, String warna, int jum, String ftr, Long id) {
        super(plat, model, merk, tahun, status, BBM, harga, warna, id);
        this.jumlah = jum;
        this.fitur = ftr;
    }   

    // setter getter
    public void setJumlah(int jum) {
        this.jumlah = jum;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setFitur(String ftr) {
        this.fitur = ftr;
    }

    public String getFitur() {
        return fitur;
    }

    @Override
    public double hitungBiaya(int lamaSewa) {
        return getHarga() * lamaSewa;
    }
}
