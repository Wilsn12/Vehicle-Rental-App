package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Motor;
public interface MotorRepository extends JpaRepository<Motor, Long> {}
