package com.example.demo.Controllers;

import org.springframework.stereotype.Component;

@Component
public class AuthUtilities {

    public String getUsername(String authorizationHeader){
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        String credentials = new String(java.util.Base64.getDecoder().decode(base64Credentials));

        // Assuming the format is username:password
        String[] parts = credentials.split(":", 2);
        String username = parts[0];
        return username;
    }


}
