package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;


import java.util.Collection;

@Data
@Builder
public class Adopter  {
    private int adopterId;
    private String name;
    private String phone;
    private String email;
    private String password;
}
