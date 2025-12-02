package com.example.demospringboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Penyewa;
public interface PenyewaRepository extends JpaRepository<Penyewa, Long> {
        Penyewa findPenyewaByKode(String kode);
}
