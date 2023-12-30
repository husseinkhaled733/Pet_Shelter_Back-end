package com.example.demo.Controllers;

import com.example.demo.DAO.AdopterDAO;
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

    @GetMapping("/adopter/search")
    public List<Pet> searchForPet(@RequestBody SearchRequestWrapper body) {
        return adopterService.searchForPet(body);
    }

    @PostMapping("/adopter/application")
    public String submitApplication(@RequestBody ApplicationRequestWrapper body) {
        adopterService.submitApplication(body);
        return "successfully submitted application";
    }

    @GetMapping("/adopter/viewApplications/{adopterEmail}")
    public List<Application> viewApplications(@PathVariable String adopterEmail) {
        if(!adopterService.emailExistsInAdopter(adopterEmail)){
            throw new RuntimeException("Adopter not found");
        }

        return adopterService.viewApplications(adopterEmail);
    }


}
