package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Kendaraan;
public interface KendaraanRepository extends JpaRepository<Kendaraan, Long> {
     Kendaraan findByPlat(String plat);
}