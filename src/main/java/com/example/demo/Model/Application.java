package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Application {
    private int petId;
    private int adopterId;
    private String status;
}
