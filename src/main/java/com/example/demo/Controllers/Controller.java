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

//    @PostMapping("/addManager")
//    public String addNewManager(@RequestBody Staff manager){
//        if(managerService.emailExistsInStaff(manager.getEmail())){
//            return "Email already exists";
//        }
//        managerService.addNewManager(manager);
//        return "Manager added successfully";
//    }

    @PostMapping("/manager/addShelter")
    public String addNewShelter(@RequestBody AddShelterRequestWrapper body){
        Shelter shelter = body.getShelter();
        Staff manager = managerService.getStaffByEmail(body.getManagerEmail()).get();
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
        managerService.addNewShelter(shelter, manager.getEmail());
        return "Shelter added successfully";
    }

    @PostMapping("/manager/addStaffMember")
    public String addNewStaff(@RequestBody AddStaffRequestWrapper body){
        Staff staff = body.getStaff();
        Staff manager = managerService.getStaffByEmail(body.getManagerEmail()).get();
        //requires staff, manager
        if(!managerService.staffExists(manager.getStaffID())){
            return "Manager not found";
        }
        if(managerService.emailExistsInStaff(staff.getEmail())){
            return "Staff email already exists";
        }
        if(!managerService.addNewStaff(staff, manager.getEmail())){
            return "Error";
        }
        return "Staff member added successfully";
    }

    @GetMapping("/manager/shelter/{shelterId}")
    public Shelter getShelterById(@PathVariable int shelterId){
        return managerService.getShelterById(shelterId).orElse(
                Shelter.builder().name("Shelter Not Found").build()
        );
    }

    @GetMapping("/manager/shelter/{shelterId}/getStaff")
    public List<Staff> getStaffByShelterId(@PathVariable int shelterId){
        Optional<Shelter> shelterContainer
                = managerService.getShelterById(shelterId);
        if(shelterContainer.isEmpty()){return null;}
        return managerService.getAllStaffOfShelter(shelterContainer.get().getEmail());
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
        return managerService.getAllStaffOfShelter(shelterContainer.get().getEmail());
    }

    @GetMapping("/manager/getStaff/{email}")
    public Staff getStaffMemberByEmail(@PathVariable String email){
        //can be user to get any staff member by email, including
        //the manager
        Optional<Staff> memberContainer =
                managerService.getStaffByEmail(email);
        return memberContainer.orElse(null);
    }
    //TODO getStaffOfShelterByEmail

    @GetMapping("/manager/getAllStaff/{managerEmail}")
    public List<Staff> getStaffOfShelterByManagerEmail(@PathVariable String managerEmail){
        Optional<Staff> managerOptional = managerService.getStaffByEmail(managerEmail);
        if(managerOptional.isEmpty()){
            throw new RuntimeException("Manager not found");
        }
        Optional<Shelter> shelterOptional = managerService.getShelterOfStaff(managerOptional.get().getStaffID());
        if(shelterOptional.isEmpty()){
            throw new RuntimeException("No shelter is managed by this manager");
        }

        return managerService.getAllStaffOfShelter(shelterOptional.get().getEmail());
    }
    //TODO deleteStaff
    @GetMapping("/manager/deleteStaff/{staffEmail}")
    public void deleteStaff(@PathVariable String staffEmail){
        managerService.deleteStaffByEmail(staffEmail);
    }

    @GetMapping("/manager/getShelter/{managerEmail}")
    public Shelter getShelterOfManager(@PathVariable String managerEmail){
        Optional<Staff> managerOptional = managerService.getStaffByEmail(managerEmail);

        if(managerOptional.isEmpty()){
            throw new RuntimeException("Manager not found");
        }
        Optional<Shelter> shelterOptional = managerService.getShelterOfStaff(managerOptional.get().getStaffID());
        if(shelterOptional.isEmpty()){
            throw new RuntimeException("No shelter is managed by this manager");
        }
        return shelterOptional.get();
    }

    @PutMapping("/manager/updateShelter")
    public void updateShelter(@RequestBody AddShelterRequestWrapper body){
        managerService.updateShelter(body.getShelter(), body.getManagerEmail());
    }

}
