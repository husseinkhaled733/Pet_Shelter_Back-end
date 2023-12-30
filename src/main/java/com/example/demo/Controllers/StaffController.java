package com.example.demo.Controllers;

import com.example.demo.Model.*;
import com.example.demo.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/staff/addPet")
    public void addPet(@RequestBody AddPetRequestWrapper body){
        Optional<Staff> staffOptional = staffService.getStaffMemberByEmail(body.getStaffEmail());
        if(staffOptional.isEmpty()){
            throw new RuntimeException("Cannot find staff");
        }
        Pet pet = body.getPet();
        pet.setShelterID(staffOptional.get().getShelterID());
        staffService.addNewPet(pet);
    }

    //all applications in sys to view --> return petId, petName, adopterEmail, status

    @GetMapping("/staff/getApplications/{staffEmail}")
    public List<ApplicationResponseWrapper> getAlApplicationsOfShelter(@PathVariable String staffEmail){
        Optional<Staff> staffOptional
                = staffService.getStaffMemberByEmail(staffEmail);
        if(staffOptional.isEmpty()){
            throw new RuntimeException("Staff member not found");
        }
        Optional<Shelter> shelterOptional
                = staffService.getShelter(staffOptional.get().getShelterID());
        if(shelterOptional.isEmpty()){
            throw new RuntimeException("Cannot find shelter of staff");
        }
        List<Application> appList
                = staffService.getAllApplicationsOfShelter(shelterOptional.get());
        List<ApplicationResponseWrapper> response = new ArrayList<>();
        for(Application app: appList){
            Optional<Pet> petOptional = staffService.getPetById(app.getPetId());
            if(petOptional.isEmpty()){
                throw new RuntimeException("Pet not found");
            }
            Optional<Adopter> adopterOptional
                    = staffService.getAdopterById(app.getAdopterId());
            if(adopterOptional.isEmpty()){
                throw new RuntimeException("Adopter not found");
            }
            response.add(new ApplicationResponseWrapper(
                    app.getPetId(),
                    petOptional.get().getName(),
                    adopterOptional.get().getName(),
                    app.getStatus()
            ));
        }
        return response;
    }

    //set application status: adopter email, petId, status
    @PutMapping("/staff/setApplicationStatus")
    public void setApplicationStatus(@RequestBody SetStatusRequestWrapper body){
        Optional<Adopter> adopterOptional
                = staffService.getAdopterByEmail(body.getAdopterEmail());
        if(adopterOptional.isEmpty()){
            throw new RuntimeException("Adopter not found");
        }
        Optional<Application> applicationOptional = staffService.getApplication(
                adopterOptional.get().getAdopterId(),
                body.getPetId()
        );
        if(applicationOptional.isEmpty()){
            throw new RuntimeException("Cannot find application");
        }
        Optional<Pet> petOptional = staffService.getPetById(body.getPetId());
        if(petOptional.isEmpty()){
            throw new RuntimeException("Pet not found");
        }
        Pet pet = petOptional.get();
        if(!pet.isAvailable()){
            throw new RuntimeException("Pet not available");
        }
        pet.setAvailable(false);
        staffService.updatePet(pet);
        staffService.setApplicationStatus(
                applicationOptional.get(),
                body.getStatus()
        );
    }
}
