package com.example.demo.Services;

import com.example.demo.Controllers.SearchRequestWrapper;
import com.example.demo.DAO.AdopterDAO;
import com.example.demo.DAO.PetDAO;
import com.example.demo.DAO.SecDAO;
import com.example.demo.Model.Adopter;
import com.example.demo.Model.Pet;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    @Autowired
    PetDAO petDAO;

    @Autowired
    AdopterDAO adopterDAO;

    @Autowired
    SecDAO secDAO;

    public AdopterService(PetDAO petDAO, AdopterDAO adopterDAO){
        this.petDAO = petDAO;
        this.adopterDAO = adopterDAO;
    }

    public List<Pet> searchForPet(SearchRequestWrapper body) {
        return petDAO.getAllPetsWithCriteria(body);
    }

    //submit application
    public void submitApplication(int petId, String userEmail) {
        //petId, useremail
    }

    public boolean emailExistsInAdopter(String email){
        return adopterDAO.getByEmail(email).isPresent();
    }

    public void addNewAdopter(Adopter adopter){
        adopter.setPassword(BCrypt.hashpw(adopter.getPassword(), BCrypt.gensalt()));
        secDAO.addAdopter(adopter);
        adopterDAO.add(adopter);
    }


}
