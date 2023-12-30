package com.example.demo.Controllers;

import com.example.demo.DAO.AdopterDAO;
import com.example.demo.DAO.PetDAO;
import com.example.demo.Model.Application;
import com.example.demo.Model.Pet;
import com.example.demo.Services.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdopterController {


    /**
     * - search for pet
     * - fitler on pet
     * - submit application
     * - view applications
     */

    @Autowired
    AdopterService adopterService;

    @Autowired
    AdopterDAO adopterDAO;

    @Autowired
    PetDAO petDAO;

    @GetMapping("/adopter/search")
    public List<Pet> searchForPet(@RequestBody SearchNot body) {
        SearchRequestWrapper body2 = new SearchRequestWrapper();
        body2.setShelterName(body.getShelterName());
        body2.setSpecies(body.getSpecies());
        body2.setBreed(body.getBreed());
        body2.setAge(body.getAge());
        return adopterService.searchForPet(body2);
    }

    @GetMapping("/login/getPets")
    public List<Pet> getPets() {
        return petDAO.getAllPets();
    }

    @PostMapping("/adopter/application")
    public String submitApplication(@RequestBody ApplicationRequestWrapper body) {
        adopterService.submitApplication(body);
        return "successfully submitted application";
    }

    @GetMapping("/adopter/viewApplications/{adopterEmail}")
    public List<AdopterApplicationResponse> viewApplications(@PathVariable String adopterEmail) {
        if(!adopterService.emailExistsInAdopter(adopterEmail)){
            throw new RuntimeException("Adopter not found");
        }
        return adopterService.viewApplications(adopterEmail);
    }




}
