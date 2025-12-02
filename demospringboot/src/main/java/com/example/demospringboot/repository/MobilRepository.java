package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Mobil;
public interface MobilRepository extends JpaRepository<Mobil, Long> {}