package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Builder
@Data
public class Staff  {
    private int staffId;
    private String name;
    private String role;
    private String phone;
    private String email;
    private String password;
    private int shelterId;

}
