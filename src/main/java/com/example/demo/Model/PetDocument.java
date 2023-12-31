package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class PetDocument {
    private int petId;
    private String documentLink;
    private String type;
}
