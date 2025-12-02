package com.example.demospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Penyewa;
import com.example.demospringboot.repository.PenyewaRepository;
 
@Service
public class PenyewaService {
    @Autowired
    private PenyewaRepository penyewaRepository;
    public List<Penyewa> getAllPenyewa() {
    return penyewaRepository.findAll();
    }
    public Penyewa addPenyewa(Penyewa obj){
        Long id = null;
        obj.setId(id);
        return penyewaRepository.save(obj);
    }
    public Penyewa getPenyewaById(long id) {
        return penyewaRepository.findById(id).orElse(null);
    }
    public Penyewa updatePenyewa(long id, Penyewa obj) {
        obj.setId(id);
        return penyewaRepository.save(obj);
    }
    public void deletePenyewa(long id) {
        penyewaRepository.deleteById(id);
    }    
    public Penyewa findPenyewaByKode(String kode){
        return penyewaRepository.findPenyewaByKode(kode);   
    }
}

