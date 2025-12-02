package com.example.demospringboot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Bus;
import com.example.demospringboot.repository.BusRepository;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;
    
    public List<Bus> getAllBus() {
        return busRepository.findAll();
    }
    
    public Bus addBus(Bus obj){
        return busRepository.save(obj);
    }

    public Bus getBusById(long id){
        return busRepository.findById(id).orElse(null);
    }

    public Bus updateBus (long id, Bus obj){
        return busRepository.save(obj);
    }

    public void deleteBus(long id){
        busRepository.deleteById(id);
    }
    
}