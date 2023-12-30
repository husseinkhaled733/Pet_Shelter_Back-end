package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Pet {
    private int petID;
    private String name;
    private boolean gender;
    private String healthStatus;
    private LocalDate dateOfBirth;
    private String species;
    private String breed;
    private String behavior;
    private String description;
    private int shelterID;
    private boolean available;
}
