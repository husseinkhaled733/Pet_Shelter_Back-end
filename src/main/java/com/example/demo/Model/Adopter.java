package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class Adopter  {
    private String adopterId;
    private String name;
    private String phone;
    private String email;
    private String password;
}
