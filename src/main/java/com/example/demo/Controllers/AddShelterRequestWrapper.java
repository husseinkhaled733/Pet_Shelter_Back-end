package com.example.demo.Controllers;


import com.example.demo.Model.Shelter;
import com.example.demo.Model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddShelterRequestWrapper {
    private Shelter shelter;
    private String managerEmail;
}
