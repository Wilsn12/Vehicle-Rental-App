package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;

import com.example.demospringboot.entity.Kendaraan;
import com.example.demospringboot.entity.Motor;
import com.example.demospringboot.entity.Mobil;
import com.example.demospringboot.entity.Bus;
import com.example.demospringboot.entity.Penyewa;

import com.example.demospringboot.service.KendaraanService;
import com.example.demospringboot.service.MotorService;
import com.example.demospringboot.service.MobilService;
import com.example.demospringboot.service.BusService;
import com.example.demospringboot.service.PenyewaService;

@Controller
public class KendaraanController {

    @Autowired
    private KendaraanService kendaraanService;

    @Autowired
    private MotorService motorService;

    @Autowired
    private MobilService mobilService;

    @Autowired
    private BusService busService;

    @GetMapping("/rentalbus")
    public String rentalBusPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("UserPenyewa") == null) {
        return "redirect:/login";
        }
        model.addAttribute("busList", busService.getAllBus());
        return "bus.html";
    }

    @GetMapping("/rentalmotor")
    public String rentalMotorPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("UserPenyewa") == null) {
        return "redirect:/login";
        }
        model.addAttribute("motorList", motorService.getAllMotor());
        return "motor.html";
    }

    @GetMapping("/rentalmobil")
    public String rentalMobilPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("UserPenyewa") == null) {
        return "redirect:/login";
        }
        model.addAttribute("mobilList", mobilService.getAllMobil());
        return "mobil.html";
    }

    @GetMapping("/rentalkendaraan")
    public String rentalKendaraanPage(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("UserPenyewa") == null) {
            return "redirect:/login";
        }

        model.addAttribute("motorList", motorService.getAllMotor());
        model.addAttribute("mobilList", mobilService.getAllMobil());
        model.addAttribute("busList", busService.getAllBus());

        return "kendaraan";
    }

    @GetMapping("/keluar")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/perentalan")
    public String perentalan(Model model) {
        return "redirect:/rental";
    }

    //backend motor
    @GetMapping("/motor")
    public String motorPage(Model model) {
        model.addAttribute("motorList", motorService.getAllMotor());
        model.addAttribute("motor", new Motor()); 
        return "datamotor";
    }

    @GetMapping("/motor/edit/{id}")
    public String motorEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("motorList", motorService.getAllMotor());
        model.addAttribute("motor", motorService.getMotorById(id)); 
        return "datamotor";
    }

    @PostMapping("/motor/save")
    public String motorSave(@ModelAttribute("motor") Motor motor) {
        motorService.addMotor(motor); 
        return "redirect:/motor";
    }

    @GetMapping("/motor/delete/{id}")
    public String motorDelete(@PathVariable("id") Long id) {
        motorService.deleteMotor(id);
        return "redirect:/motor";
    }

    //backend mobil
    @GetMapping("/mobil")
    public String mobilPage(Model model) {
        model.addAttribute("mobilList", mobilService.getAllMobil());
        model.addAttribute("mobil", new Mobil()); 
        return "datamobil";
    }

    @GetMapping("/mobil/edit/{id}")
    public String mobilEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("mobilList", mobilService.getAllMobil());
        model.addAttribute("mobil", mobilService.getMobilById(id)); 
        return "datamobil";
    }

    @PostMapping("/mobil/save")
    public String mobilSave(@ModelAttribute("mobil") Mobil mobil) {
        mobilService.addMobil(mobil); 
        return "redirect:/mobil";
    }

    @GetMapping("/mobil/delete/{id}")
    public String mobilDelete(@PathVariable("id") Long id) {
        mobilService.deleteMobil(id);
        return "redirect:/mobil";
    }

    //backend bus
    @GetMapping("/bus")
    public String busPage(Model model) {
        model.addAttribute("busList", busService.getAllBus());
        model.addAttribute("bus", new Bus()); 
        return "databus";
    }

    @GetMapping("/bus/edit/{id}")
    public String busEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("busList", busService.getAllBus());
        model.addAttribute("bus", busService.getBusById(id)); 
        return "databus";
    }

    @PostMapping("/bus/save")
    public String busSave(@ModelAttribute("bus") Bus bus) {
        busService.addBus(bus); 
        return "redirect:/bus";
    }

    @GetMapping("/bus/delete/{id}")
    public String busDelete(@PathVariable("id") Long id) {
        busService.deleteBus(id);
        return "redirect:/bus";
    }


}