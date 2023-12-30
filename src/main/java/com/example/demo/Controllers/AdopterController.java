package com.example.demo.Controllers;

import com.example.demo.Model.Pet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class AdopterController {


    /**
     * - search for pet
     * - fitler on pet
     * - submit application
     * - view applications
     */

    @GetMapping("/adopter/search")
    public List<Pet> searchForPet() {
        return null;
    }

    @PostMapping("/adopter/application")
    public String submitApplication() {
        return null;
    }

    @GetMapping("/adopter/viewApplications")
    public String viewApplications() {
        return null;
    }


}
