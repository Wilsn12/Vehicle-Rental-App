    package com.example.demospringboot.service;
    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.example.demospringboot.entity.Kendaraan;
    import com.example.demospringboot.entity.Rental;
    import com.example.demospringboot.entity.Penyewa;
    import com.example.demospringboot.repository.KendaraanRepository;
    import com.example.demospringboot.repository.RentalRepository;
    import com.example.demospringboot.repository.PenyewaRepository;
    @Service
    public class RentalService {
        @Autowired
        private RentalRepository rentalRepository;

        @Autowired
        private KendaraanRepository kendaraanRepository;

        @Autowired
        private PenyewaRepository penyewaRepository;


        public List<Rental> findAll() {
            return rentalRepository.findAll();
        }

        public Rental findById(String id) {
            return rentalRepository.findById(id).orElse(null);
        }

    public Rental createRental(Rental rental) {

        if (rental.getPenyewa() == null || rental.getPenyewa().getKode() == null) {
            return null;
        }

        if (rental.getKendaraan() == null || rental.getKendaraan().getPlat() == null) {
            return null;
        }

        Penyewa penyewa = penyewaRepository.findPenyewaByKode(rental.getPenyewa().getKode());
        Kendaraan kendaraan = kendaraanRepository.findByPlat(rental.getKendaraan().getPlat());

        rental.setPenyewa(penyewa);
        rental.setKendaraan(kendaraan);

        double total = kendaraan.hitungBiaya(rental.getLamaSewa());
        rental.setTotalBiaya(total); 

        kendaraan.setStatus("Disewa");
        kendaraanRepository.save(kendaraan);

        return rentalRepository.save(rental);
    }
public Rental updateRental(Rental rental) {
    Rental old = rentalRepository.findById(rental.getRentalID()).orElse(null);
    if (old == null) return null;

    old.setLamaSewa(rental.getLamaSewa());
    old.setTotalBiaya(rental.getKendaraan().getHarga() * rental.getLamaSewa());
    old.setPenyewa(rental.getPenyewa());
    old.setKendaraan(rental.getKendaraan());

    if (rental.getKendaraan() != null) {
        rental.getKendaraan().setStatus("Disewa");
        kendaraanRepository.save(rental.getKendaraan());
    }

    return rentalRepository.save(old);
}



        public void delete(String id) {
            Rental rental = rentalRepository.findById(id).orElse(null);
            if (rental != null) {
                Kendaraan k = rental.getKendaraan();
                if (k != null) {
                    k.setStatus("Tersedia");
                    kendaraanRepository.save(k);
                }
            }
            rentalRepository.deleteById(id);
        }

        public Rental findByPenyewa(String penyewaId) {
            return rentalRepository.findByPenyewaKode(penyewaId);
        }
        public Rental findByKendaraan(String plat) {
            return rentalRepository.findByKendaraanPlat(plat);
        }
    }
