package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class PetImage {
    private int petID;
    private String imageLink;
}
