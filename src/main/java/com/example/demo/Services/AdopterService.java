package com.example.demo.Services;

import com.example.demo.Controllers.AdopterApplicationResponse;
import com.example.demo.Controllers.ApplicationRequestWrapper;
import com.example.demo.Controllers.SearchRequestWrapper;
import com.example.demo.DAO.AdopterDAO;
import com.example.demo.DAO.ApplicationDAO;
import com.example.demo.DAO.PetDAO;
import com.example.demo.DAO.SecDAO;
import com.example.demo.Model.Adopter;
import com.example.demo.Model.Application;
import com.example.demo.Model.Pet;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdopterService {

    @Autowired
    PetDAO petDAO;

    @Autowired
    AdopterDAO adopterDAO;

    @Autowired
    SecDAO secDAO;

    @Autowired
    ApplicationDAO applicationDAO;

    public AdopterService(PetDAO petDAO, AdopterDAO adopterDAO){
        this.petDAO = petDAO;
        this.adopterDAO = adopterDAO;
    }

    public List<Pet> searchForPet(SearchRequestWrapper body) {
        return petDAO.getAllPetsWithCriteria(body);
    }

    //submit application
    public void submitApplication(ApplicationRequestWrapper body ) {
        System.out.println(body.getAdopterEmail());
        Optional<Adopter> adopterContainer = adopterDAO.getByEmail(body.getAdopterEmail());
        if(adopterContainer.isEmpty()){
            throw new RuntimeException("Adopter not found");
        }
        applicationDAO.addNewApplication(adopterContainer.get().getAdopterId(), body.getPetId());
    }

    public boolean emailExistsInAdopter(String email){
        return adopterDAO.getByEmail(email).isPresent();
    }

    public void addNewAdopter(Adopter adopter){
        adopter.setPassword(BCrypt.hashpw(adopter.getPassword(), BCrypt.gensalt()));
        secDAO.addAdopter(adopter);
        adopterDAO.add(adopter);
    }

    public List<AdopterApplicationResponse> viewApplications(String adopterEmail){
        Optional<Adopter> adopterContainer = adopterDAO.getByEmail(adopterEmail);
        if(adopterContainer.isEmpty()){
            throw new RuntimeException("Adopter not found");
        }
        List<Application> applications = applicationDAO.getApplicationsOfAdopter(adopterContainer.get().getAdopterId());
        System.out.println(adopterContainer.get().getAdopterId());
        //loop over this applications list and cereate new list called response list of type AdopterApplicationResponse
        //for each application, get the pet name from pet table and add it to the response list with status from applications list
        List<AdopterApplicationResponse> responseList = new ArrayList<>();
        for(Application a: applications){
            AdopterApplicationResponse response = new AdopterApplicationResponse();
            System.out.println(a.getPetId());
            Optional<Pet> petContainer = petDAO.get(a.getPetId());
            if(petContainer.isEmpty()){
                throw new RuntimeException("Pet not found");
            }
            response.setPetName(petContainer.get().getName());
            response.setStatus(a.getStatus());
            responseList.add(response);
        }
        return responseList;
    }


}
