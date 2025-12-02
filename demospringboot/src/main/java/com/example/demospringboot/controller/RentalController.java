package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demospringboot.entity.Rental;
import com.example.demospringboot.entity.Kendaraan;
import com.example.demospringboot.entity.Penyewa;

import com.example.demospringboot.service.RentalService;
import com.example.demospringboot.service.KendaraanService;
import com.example.demospringboot.service.PenyewaService;

@Controller
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private KendaraanService kendaraanService;

    @Autowired
    private PenyewaService penyewaService;

    @GetMapping({"", "/"})
    public String rentalPage(Model model) {
        model.addAttribute("rentalList", rentalService.findAll());
        model.addAttribute("rentalInfo", new Rental());
        model.addAttribute("penyewaList", penyewaService.getAllPenyewa());
        model.addAttribute("kendaraanList", kendaraanService.getAllKendaraan());
        return "rental.html";
    }

    @GetMapping("/{id}")
    public String rentalGetRec(Model model, @PathVariable("id") String id) {
        model.addAttribute("rentalList", rentalService.findAll());
        Rental r = rentalService.findById(id);
        if (r == null) r = new Rental();
        model.addAttribute("rentalInfo", r);
        model.addAttribute("penyewaList", penyewaService.getAllPenyewa());
        model.addAttribute("kendaraanList", kendaraanService.getAllKendaraan());
        return "rental.html";
    }

    @PostMapping("/submit")
    public String rentalSubmit(
            @RequestParam("penyewaKode") String penyewaKode,
            @RequestParam("kendaraanPlat") String kendaraanPlat,
            @ModelAttribute("rentalInfo") Rental rentalInfo) {

        Penyewa penyewa = penyewaService.findPenyewaByKode(penyewaKode);
        Kendaraan kendaraan = kendaraanService.findByPlat(kendaraanPlat);

        rentalInfo.setPenyewa(penyewa);
        rentalInfo.setKendaraan(kendaraan);

        rentalInfo.setTotalBiaya(kendaraan.getHarga() * rentalInfo.getLamaSewa());

        rentalService.createRental(rentalInfo);

        kendaraan.setStatus("Disewa");
        kendaraanService.save(kendaraan);

        return "redirect:/rental";
    }

@PostMapping("/submit/{id}")
public String rentalUpdate(
        @PathVariable("id") String id,
        @RequestParam("penyewaKode") String penyewaKode,
        @RequestParam("kendaraanPlat") String kendaraanPlat,
        @ModelAttribute("rentalInfo") Rental rentalInfo) {

    Rental existing = rentalService.findById(id);
    if (existing == null) return "redirect:/rental";

    if (existing.getKendaraan() != null) {
        existing.getKendaraan().setStatus("Tersedia");
        kendaraanService.save(existing.getKendaraan());
    }

    Penyewa penyewa = penyewaService.findPenyewaByKode(penyewaKode);
    Kendaraan kendaraan = kendaraanService.findByPlat(kendaraanPlat);

    existing.setPenyewa(penyewa);
    existing.setKendaraan(kendaraan);
    existing.setLamaSewa(rentalInfo.getLamaSewa());
    existing.setTotalBiaya(kendaraan.getHarga() * rentalInfo.getLamaSewa());

    rentalService.updateRental(existing);

    kendaraan.setStatus("Disewa");
    kendaraanService.save(kendaraan);

    return "redirect:/rental";
}


    
    @GetMapping("/delete/{id}")
    public String rentalDelete(@PathVariable("id") String id) {
        Rental r = rentalService.findById(id);

        if (r != null && r.getKendaraan() != null) {
            r.getKendaraan().setStatus("Tersedia");
            kendaraanService.save(r.getKendaraan());
        }

        rentalService.delete(id);
        return "redirect:/rental";
    }
}

