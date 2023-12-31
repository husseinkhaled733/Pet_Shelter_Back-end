package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Staff {

    private int staffID;
    private String name;
    private String role;
    private String phone;
    private String email;
    private String password;
    private int shelterID;
}
