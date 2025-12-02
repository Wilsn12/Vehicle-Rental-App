package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Person;
import com.example.demospringboot.entity.Petugas;
import com.example.demospringboot.entity.Penyewa;

import com.example.demospringboot.service.PersonService;
import com.example.demospringboot.service.PetugasService;
import com.example.demospringboot.service.PenyewaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PetugasService petugasService;
    @Autowired
    private PenyewaService penyewaService;

    @GetMapping(value = {"/person", "/person/"})
    public String PersonPage(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("UserPenyewa") != null) {
            return "redirect:/kendaraan";
        }

        if (request.getSession().getAttribute("UserPetugas") != null) {
            List<Person> personList = personService.getAllPerson();
            model.addAttribute("personList", personList);
            model.addAttribute("personInfo", new Person());
            model.addAttribute("logUser", request.getSession().getAttribute("UserPetugas"));
            return "person";
        } 
        
        else {
            return "redirect:/login";
        }
    }

    @PostMapping(value={"/person/submit/", "/person/submit/{id}"}, params={"add"})
    public String personAdd(@ModelAttribute("personInfo") Person personInfo){
        personService.addPerson(personInfo);
        return "redirect:/person";
    }

    @PostMapping(value="/person/submit/{id}", params={"edit"})
    public String personEdit(@ModelAttribute("personInfo") Person personInfo, @PathVariable("id") int id){
        personService.updatePerson(id, personInfo);
        return "redirect:/person";
    }

    @PostMapping(value="/person/submit/{id}", params={"delete"})
    public String personDelete(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/person";
    }


    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            return "redirect:/petugas";
        }
        if(request.getSession().getAttribute("UserPenyewa") != null){
            return "redirect:/kendaraan";
        }   
        return "login"; 
    }

    @PostMapping("/validateLogin")
    public String validateLogin(Model model, 
                                @RequestParam("kode") String kode,
                                @RequestParam("password") String password,
                                HttpServletRequest request) {
        
        Petugas petugas = petugasService.findPetugasByKode(kode);
        if (petugas != null && password.equals(petugas.getPassword())) {
            request.getSession().setAttribute("UserPetugas", petugas);
            return "redirect:/petugas"; 
        }

        Penyewa penyewa = penyewaService.findPenyewaByKode(kode);
        if (penyewa != null && password.equals(penyewa.getPassword())) {
            request.getSession().setAttribute("UserPenyewa", penyewa);
            return "redirect:/kendaraan";
        }

        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login"; 
    }
    
    @GetMapping(value={"/petugas", "/petugas/"})
    public String petugasPage(Model model, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPenyewa") != null){
            return "redirect:/kendaraan"; 
        }
        
        if(request.getSession().getAttribute("UserPetugas") != null){
            List<Petugas> list = petugasService.getAllPetugas();
            model.addAttribute("petugasList", list);
            model.addAttribute("petugasInfo", new Petugas()); 
            model.addAttribute("logUser", request.getSession().getAttribute("UserPetugas"));
            return "petugas"; 
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/petugas/{id}")
    public String petugasGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {
         if(request.getSession().getAttribute("UserPetugas") != null){
            List<Petugas> list = petugasService.getAllPetugas();
            Petugas target = petugasService.getPetugasById(id);
            model.addAttribute("petugasList", list);
            model.addAttribute("petugasInfo", target); 
            model.addAttribute("logUser", request.getSession().getAttribute("UserPetugas"));
            return "petugas";
         } else {
            return "redirect:/login";
         }
    }

    @PostMapping(value={"/petugas/submit/", "/petugas/submit/{id}"}, params={"add"})
    public String petugasAdd(@ModelAttribute("petugasInfo") Petugas petugasInfo, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            petugasService.addPetugas(petugasInfo);
            return "redirect:/petugas";
        }
        return "redirect:/login";
    }
    
    @PostMapping(value="/petugas/submit/{id}", params={"edit"})
    public String petugasEdit(@ModelAttribute("petugasInfo") Petugas petugasInfo, @PathVariable("id") int id, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            petugasService.updatePetugas(id, petugasInfo);
            return "redirect:/petugas";
        }
        return "redirect:/login";
    }

    @PostMapping(value="/petugas/submit/{id}", params={"delete"})
    public String petugasDelete(@PathVariable("id") int id, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            petugasService.deletePetugas(id);
            return "redirect:/petugas";
        }
        return "redirect:/login";
    }

    @GetMapping(value={"/penyewa", "/penyewa/"})
    public String penyewaPage(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("UserPenyewa") != null) {
            return "redirect:/kendaraan"; 
        }
        
        if (request.getSession().getAttribute("UserPetugas") != null) {
            List<Penyewa> list = penyewaService.getAllPenyewa();
            model.addAttribute("penyewaList", list);
            model.addAttribute("penyewaInfo", new Penyewa()); 
            model.addAttribute("logUser", request.getSession().getAttribute("UserPetugas"));
            
            return "penyewa"; 
        }
        
        else {
            return "redirect:/login";
        }
    }
    
    @GetMapping("/penyewa/{id}")
    public String penyewaGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {
         if(request.getSession().getAttribute("UserPetugas") != null){
            List<Penyewa> list = penyewaService.getAllPenyewa();
            Penyewa target = penyewaService.getPenyewaById(id);
            model.addAttribute("penyewaList", list);
            model.addAttribute("penyewaInfo", target); 
            model.addAttribute("logUser", request.getSession().getAttribute("UserPetugas"));
            return "penyewa"; 
         } else {
            return "redirect:/login";
         }
    }

    @PostMapping(value={"/penyewa/submit/", "/penyewa/submit/{id}"}, params={"add"})
    public String penyewaAdd(@ModelAttribute("penyewaInfo") Penyewa penyewaInfo, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            penyewaService.addPenyewa(penyewaInfo);
            return "redirect:/penyewa";
        }
        return "redirect:/login";
    }

    @PostMapping(value="/penyewa/submit/{id}", params={"delete"})
    public String penyewaDelete(@PathVariable("id") int id, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            penyewaService.deletePenyewa(id);
            return "redirect:/penyewa";
        }
        return "redirect:/login";
    }

    @PostMapping(value="/penyewa/submit/{id}", params={"edit"})
    public String penyewaEdit(@ModelAttribute("penyewaInfo") Penyewa penyewaInfo, @PathVariable("id") int id, HttpServletRequest request) {
        if(request.getSession().getAttribute("UserPetugas") != null){
            penyewaService.updatePenyewa(id, penyewaInfo);
            return "redirect:/penyewa";
        }
        return "redirect:/login";
    }

    @GetMapping("/kendaraan")
    public String kendaraanPage(Model model, HttpServletRequest request) {
        Penyewa p = (Penyewa) request.getSession().getAttribute("UserPenyewa");
        
        if(p == null){
            if(request.getSession().getAttribute("UserPetugas") != null) {
                return "redirect:/petugas"; 
            }
            return "redirect:/login";
        }

        model.addAttribute("logUser", p);
        return "kendaraan"; 
    }


    @GetMapping("/registerpenyewa") 
    public String registerPenyewaPage(Model model) {
        model.addAttribute("penyewaInfo", new Penyewa()); 
        return "registerpenyewa"; 
    }

    @PostMapping("/registerpenyewa/submit") 
    public String registerPenyewaSubmit(@ModelAttribute("penyewaInfo") Penyewa penyewa) {
        penyewaService.addPenyewa(penyewa);
        return "redirect:/login"; 
    }

    @GetMapping("/registerpetugas") 
    public String registerPetugasPage(Model model) {
        model.addAttribute("petugasInfo", new Petugas()); 
        return "registerpetugas"; 
    }

    @PostMapping("/registerpetugas/submit") 
    public String registerPetugasSubmit(@ModelAttribute("petugasInfo") Petugas petugas) {
        petugasService.addPetugas(petugas);
        return "redirect:/login"; 
    }
}