package com.example.demo.Controllers;

import com.example.demo.Services.ManagerService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/adopter/test")
    public String getTestA() {
        return BCrypt.hashpw("test123", BCrypt.gensalt());
    }

    @GetMapping("/manager/test")
    public String getTestM() {
        return BCrypt.hashpw("test123", BCrypt.gensalt());
    }

    @GetMapping("/staff/test")
    public String getTestS() {
        return "Hello World staff";
    }

//    @GetMapping("/admin/test")
//    public String getTestSAdmin(@RequestBody StaffDTO staffDTO ) {
//        managerService.addNewManager(staffDTO);
//        return "Manager added successfully";
//    }
}
