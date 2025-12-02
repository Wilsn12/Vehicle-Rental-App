package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String> {

    Rental findByPenyewaKode(String kode);

    Rental findByKendaraanPlat(String plat);
}
