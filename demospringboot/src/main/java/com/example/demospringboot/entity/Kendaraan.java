    package com.example.demospringboot.entity;
    import jakarta.persistence.Entity;
    import jakarta.persistence.Inheritance;
    import jakarta.persistence.InheritanceType;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.validation.constraints.Size;

    @Entity
    @Inheritance(strategy = InheritanceType.JOINED)
    public abstract class Kendaraan {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Size(min = 8, max = 9)
        private String plat;

        private String model;

        private String merk;

        private int tahun;

        private String status;

        private String BBM;

        private double harga;

        private String warna;

        public Kendaraan() {}

        public Kendaraan (String plat, String model, String merk, int tahun, String status, String BBM, double harga, String warna, Long id){
            this.plat = plat;
            this.model = model;
            this.merk = merk;
            this.tahun = tahun;
            this.status = status;
            this.BBM = BBM;
            this.harga = harga;
            this.warna = warna;
            this.id = id;
        }

        public void setPlat(String plat) {
            this.plat = plat;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public void setTahun(int tahun) {
            this.tahun = tahun;
        }

        public void setMerk(String merk) {
            this.merk = merk;
        }

        public void setBBM(String BBM) {
            this.BBM = BBM;
        }

        public void setStatus(String status){
            this.status = status;
        }

        public void setHarga(double harga){
            this.harga = harga;
        }

        public void setWarna(String warna){
            this.warna = warna;
        }

        public void setId(Long id) {
            this.id = id;
        }

        // Getter methods
        public String getPlat(){
            return plat;
        }

        public String getModel(){
            return model;
        }

        public String getMerk(){
            return merk;
        }

        public int getTahun(){
            return tahun;
        }

        public String getStatus(){
            return status;
        }

        public String getBBM(){
            return BBM;
        }

        public double getHarga(){
            return harga;
        }

        public String getWarna(){
            return warna;
        }

        public Long getId() {
            return id;
        }

        public abstract double hitungBiaya(int lamaSewa);
    }
