package com.example.demospringboot.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Motor;
import com.example.demospringboot.repository.MotorRepository;

@Service
public class MotorService {
    @Autowired
    private MotorRepository motorRepository;

    public List<Motor> getAllMotor() {
        return motorRepository.findAll();
    }

    public Motor addMotor(Motor obj) {
        return motorRepository.save(obj);
    }

    public Motor getMotorById(long id) {
        return motorRepository.findById(id).orElse(null);
    }

    public Motor updateMotor(long id, Motor obj) {
        return motorRepository.save(obj);
    }

    public void deleteMotor(long id) {
        motorRepository.deleteById(id);
    }
    
}
