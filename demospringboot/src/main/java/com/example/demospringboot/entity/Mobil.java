package com.example.demospringboot.entity;
import jakarta.persistence.Entity;

@Entity
public class Mobil extends Kendaraan {
    private String jenis;
    private int kapasitas;
    private String fasilitas;
    private String transmisi;

    // constructor
    public Mobil() {}

    public Mobil(String plat, String model, String merk, int tahun, String status, String BBM, double harga, String warna, String j, int k, String f, String t, Long id) {
        super(plat, model, merk, tahun, status, BBM, harga, warna, id);
        this.jenis = j;
        this.kapasitas = k;
        this.fasilitas = f;
        this.transmisi = t;
    }

    // setter getter
    public void setJenis(String j) {
        this.jenis = j;
    }

    public String getJenis() {
        return jenis;
    }

    public void setKapasitas(int k) {
        this.kapasitas = k;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public void setFasilitas(String f) {
        this.fasilitas = f;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setTransmisi(String t) {
        this.transmisi = t;
    }

    public String getTransmisi() {
        return transmisi;
    }

    @Override
    public double hitungBiaya(int lamaSewa) {
        return getHarga() * lamaSewa;
    }
}
