    package com.example.demospringboot.entity;

    import jakarta.persistence.Entity;
    import jakarta.persistence.Id;
    import jakarta.persistence.ManyToOne;

    @Entity
    public class Rental {

        @Id
        private String rentalID;

        private int lamaSewa;
        private double totalBiaya;

        @ManyToOne
        private Kendaraan kendaraan;

        @ManyToOne
        private Petugas petugas;

        @ManyToOne
        private Penyewa penyewa;

        public Rental() {}

        public Rental(String rentalID, int lamaSewa, double totalBiaya,
                    Kendaraan kendaraan, Petugas petugas, Penyewa penyewa) {

            this.rentalID = rentalID;
            this.lamaSewa = lamaSewa;
            this.totalBiaya = totalBiaya;
            this.kendaraan = kendaraan;
            this.petugas = petugas;
            this.penyewa = penyewa;
        }

        public String getRentalID() { return rentalID; }
        public void setRentalID(String rentalID) { this.rentalID = rentalID; }

        public int getLamaSewa() { return lamaSewa; }
        public void setLamaSewa(int lamaSewa) { this.lamaSewa = lamaSewa; }

        public double getTotalBiaya() { return totalBiaya; }
        public void setTotalBiaya(double totalBiaya) { this.totalBiaya = totalBiaya; }

    public Kendaraan getKendaraan() { return kendaraan; }
    public void setKendaraan(Kendaraan kendaraan) { this.kendaraan = kendaraan; }

        public Petugas getPetugas() { return petugas; }
        public void setPetugas(Petugas petugas) { this.petugas = petugas; }

        public Penyewa getPenyewa() { return penyewa; }
    public void setPenyewa(Penyewa penyewa) { this.penyewa = penyewa; }
    }
