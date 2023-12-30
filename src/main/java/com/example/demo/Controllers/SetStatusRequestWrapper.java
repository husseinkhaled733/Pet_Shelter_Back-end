package com.example.demo.Controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SetStatusRequestWrapper {
    private String adopterEmail;
    private int petId;
    private String status;
}
