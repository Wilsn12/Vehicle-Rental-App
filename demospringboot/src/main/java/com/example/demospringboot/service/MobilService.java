package com.example.demospringboot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Mobil;
import com.example.demospringboot.repository.MobilRepository;

@Service
public class MobilService {
    @Autowired
    private MobilRepository mobilRepository;

    public List<Mobil> getAllMobil() {
        return mobilRepository.findAll();
    }

    public Mobil addMobil(Mobil obj) {
        return mobilRepository.save(obj);
    }

    public Mobil getMobilById(long id) {
        return mobilRepository.findById(id).orElse(null);
    }

    public Mobil updateMobil(long id, Mobil obj) {
        return mobilRepository.save(obj);
    }

    public void deleteMobil(long id) {
        mobilRepository.deleteById(id);
    }
    
}
