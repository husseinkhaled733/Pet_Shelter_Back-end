package com.example.demo.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDTO {
    private String name;
    private String role;
    private String phone;
    private String email;
    private String password;
}
