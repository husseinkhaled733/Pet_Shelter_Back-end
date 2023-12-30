package com.example.demo.Controllers;

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

    @GetMapping("/adopter/search")
    public List<Pet> searchForPet(@RequestBody SearchRequestWrapper body) {
        return adopterService.searchForPet(body);
    }

    @PostMapping("/adopter/application")
    public String submitApplication(@RequestBody ApplicationRequestWrapper body) {
        //petId, useremail
        return null;
    }

    @GetMapping("/adopter/viewApplications")
    public String viewApplications() {
        return null;
    }


}
