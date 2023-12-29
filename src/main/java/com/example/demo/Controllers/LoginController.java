package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    AuthUtilities authUtilities;

    @GetMapping("/login")
    public String Login(@RequestHeader("Authorization") String authorizationHeader){
        authUtilities.getUsername(authorizationHeader);
        return "Successful Login";
    }

//    @GetMapping("/Login")
//    public String Login(){
//
//        // Do something with the username
//        return "Successful Login";
//    }
}