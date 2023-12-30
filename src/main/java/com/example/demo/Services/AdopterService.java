package com.example.demo.Services;

import com.example.demo.DAO.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdopterService {

    @Autowired
    PetDAO petDAO;


    public AdopterService(PetDAO petDAO){
        this.petDAO = petDAO;
    }
}
