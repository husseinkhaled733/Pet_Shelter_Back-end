package com.example.demo.Controllers;



import com.example.demo.Model.Shelter;
import com.example.demo.Model.Staff;
import com.example.demo.Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class Controller {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/addManager")
    public String addNewManager(@RequestBody Staff manager){
        if(managerService.emailExistsInStaff(manager.getEmail())){
            return "Email already exists";
        }
        managerService.addNewManager(manager);
        return "Manager added successfully";
    }

    @PostMapping("/addShelter")
    public String addNewShelter(@RequestBody AddShelterRequestWrapper body){
        Shelter shelter = body.getShelter();
        Staff manager = body.getManager();
        System.out.println(manager);
        //requires: shelter, manager
        if(managerService.emailExistsInShelters(shelter.getEmail())){
            return "Shelter email already exists";
        }
        if(!managerService.staffExists(manager.getStaffID())){
            return "Manager not found";
        }
        if(managerService.getShelterOfStaff(manager.getStaffID()).isPresent()){
            return "Manager already manages a shelter";
        }
        managerService.addNewShelter(shelter, manager);
        return "Shelter added successfully";
    }

    @PostMapping("/addStaffMember")
    public String addNewStaff(@RequestBody AddStaffRequestWrapper body){
        Staff staff = body.getStaff();
        Staff manager = body.getManager();
        //requires staff, manager
        if(!managerService.staffExists(manager.getStaffID())){
            return "Manager not found";
        }
        if(managerService.emailExistsInStaff(staff.getEmail())){
            return "Staff email already exists";
        }
        if(!managerService.addNewStaff(staff, manager)){
            return "Error";
        }
        return "Staff member added successfully";
    }

    @GetMapping("/shelter/{shelterId}")
    public Shelter getShelterById(@PathVariable int shelterId){
        return managerService.getShelterById(shelterId).orElse(
                Shelter.builder().name("Shelter Not Found").build()
        );
    }

    @GetMapping("/shelter/{shelterId}/getStaff")
    public List<Staff> getStaffByShelterId(@PathVariable int shelterId){
        Optional<Shelter> shelterContainer
                = managerService.getShelterById(shelterId);
        if(shelterContainer.isEmpty()){return null;}
        return managerService.getAllStaffOfShelter(shelterContainer.get());
    }

    @GetMapping("/manager/{managerId}/getStaff")
    public List<Staff> getStaffByManagerId(@PathVariable int managerId){
        Optional<Staff> managerContainer
                = managerService.getStaffById(managerId);
        if(managerContainer.isEmpty()){return null;}
        Optional<Shelter> shelterContainer
                = managerService.getShelterById(
                        managerContainer.get().getShelterID()
        );
        if(shelterContainer.isEmpty()){return null;}
        return managerService.getAllStaffOfShelter(shelterContainer.get());
    }

    @GetMapping("/staff/{email}")
    public Staff getStaffMemberByEmail(@PathVariable String email){
        //can be user to get any staff member by email, including
        //the manager
        Optional<Staff> memberContainer =
                managerService.getStaffByEmail(email);
        return memberContainer.orElse(null);
    }
    //TODO getStaffOfShelterByEmail
    //TODO deleteStaff

}
