package com.example.demo.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApplicationResponseWrapper {
    private int petId;
    private String petName;
    private String adopterEmail;
    private String status;
}
