    package com.example.demospringboot.entity;

    import jakarta.persistence.Entity;

    @Entity
    public class Penyewa extends Person implements Login{
        private String alamat;
        private String noSIM;

        public void signup(){

        }

        public void signin(){
            
        }

        public Penyewa() {
        }

        public Penyewa(String kode, String name, String noHP, String password, String alamat, String noSIM) {
            super(kode, name, noHP, password);
            this.alamat = alamat;
            this.noSIM = noSIM;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }
        public String getAlamat() {
            return alamat;
        }
        public void setNoSIM(String noSIM) {
            this.noSIM = noSIM;
        }
        public String getNoSim() {
            return noSIM;
        }
    }
