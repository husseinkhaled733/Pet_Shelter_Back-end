package com.example.demo.Controllers;

import lombok.Data;

@Data
public class SearchRequestWrapper {

    private String shelterName;
    private String species;
    private String breed;
    private int age;
}
