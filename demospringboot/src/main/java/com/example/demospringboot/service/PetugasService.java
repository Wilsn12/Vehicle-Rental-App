package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Petugas;
import com.example.demospringboot.repository.PetugasRepository;
 
@Service
public class PetugasService {
    @Autowired
    private PetugasRepository petugasRepository;
    public List<Petugas> getAllPetugas() {
    return petugasRepository.findAll();
    }
    public Petugas addPetugas(Petugas obj){
        Long id = null;
        obj.setId(id);
        return petugasRepository.save(obj);
    }
    public Petugas getPetugasById(long id) {
        return petugasRepository.findById(id).orElse(null);
    }
    public Petugas updatePetugas(long id, Petugas obj) {
        obj.setId(id);
        return petugasRepository.save(obj);
    }
    public void deletePetugas(long id) {
        petugasRepository.deleteById(id);
    }    
    public Petugas findPetugasByKode(String kode){
        return petugasRepository.findPetugasByKode(kode);   
    }
}

