package com.example.demo.Controllers;


import com.example.demo.Model.Staff;
import com.example.demo.Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RegistrationController {

    @Autowired
    ManagerService managerService;

//    @PostMapping("/register/manager")
//    public String addManager(@RequestBody StaffDTO staffDTO) {
//        managerService.addNewManager(staffDTO);
//        return "Manager added successfully";
//    }
    @PostMapping("/register/addManager")
    public String addNewManager(@RequestBody Staff manager){
        System.out.println("Adding manager requsest");
        if(managerService.emailExistsInStaff(manager.getEmail())){
            return "Email already exists";
        }
        managerService.addNewManager(manager);
        return "Manager added successfully";
    }


}
