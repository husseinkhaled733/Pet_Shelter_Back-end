package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Shelter {
    private int id;
    private String name;
    private String country;
    private String city;
    private String phone;
    private String email;
    private String detailedAddress;
    private int managerId;
}
