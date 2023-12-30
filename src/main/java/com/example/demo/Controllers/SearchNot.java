package com.example.demo.Controllers;

import lombok.Data;

@Data
public class SearchNot {
    private String shelterName;
    private String species;
    private String breed;
    private int age;

    private String shelterLocation;
    private boolean houseTrained;
    private boolean notHouseTrained;
    private boolean vaccinated;
    private boolean notVaccinated;
    private boolean spayed;
    private boolean neutering;
}
