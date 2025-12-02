package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Petugas extends Person implements Login{

    public void signup(){

    }

    public void signin(){
            
    }

    public Petugas() {
    }

    public Petugas(String kode, String name, String noHP, String password) {
        super(kode, name, noHP, password);

    }
}
