package com.example.demo.Controllers;


import com.example.demo.Model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStaffRequestWrapper {
    private Staff staff;
    private String managerEmail;
}
