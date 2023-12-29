package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Shelter {
    private int id;
    private String name;
    private String country;
    private String city;
    private String phone;
    private String email;
    private String detailedAddress;
    private int managerID;
}
