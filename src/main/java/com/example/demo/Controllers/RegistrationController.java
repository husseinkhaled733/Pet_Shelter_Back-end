package com.example.demo.Controllers;

import com.example.demo.DTOs.StaffDTO;
import com.example.demo.Services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/register/manager")
    public String addManager(@RequestBody StaffDTO staffDTO) {
        managerService.addNewManager(staffDTO);
        return "Manager added successfully";
    }


}
