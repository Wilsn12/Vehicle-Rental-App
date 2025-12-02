package com.example.demospringboot.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
 import com.example.demospringboot.entity.Petugas;
 public interface PetugasRepository extends JpaRepository<Petugas, Long>{
        Petugas findPetugasByKode(String kode);
 }
