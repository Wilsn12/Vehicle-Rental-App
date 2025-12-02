package com.example.demospringboot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Kendaraan;
import com.example.demospringboot.repository.KendaraanRepository;

@Service
public class KendaraanService {
    @Autowired
    private KendaraanRepository kendaraanRepository;
    public List<Kendaraan> getAllKendaraan() {
        return kendaraanRepository.findAll();
    }

    public Kendaraan addKendaraan(Kendaraan obj) {
        return kendaraanRepository.save(obj);
    }

    public Kendaraan getKendaraanById(long id) {
        return kendaraanRepository.findById(id).orElse(null);
    }

    public Kendaraan updateKendaraan(long id, Kendaraan obj) {
        return kendaraanRepository.save(obj);
    }

    public void deleteKendaraan(long id) {
        kendaraanRepository.deleteById(id);
    }
    public Kendaraan findByPlat(String plat) {
    return kendaraanRepository.findByPlat(plat);
    }
    public Kendaraan save(Kendaraan kendaraan) {
    return kendaraanRepository.save(kendaraan);
    }    
}
