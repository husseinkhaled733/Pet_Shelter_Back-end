package com.example.demo.Controllers;

import com.example.demo.Model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddPetRequestWrapper {
    private Pet pet;
    private String staffEmail;
}
