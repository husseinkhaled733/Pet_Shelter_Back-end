package com.example.demo.Services;


import com.example.demo.DAO.PetDAO;
import com.example.demo.DAO.PetDocumentDAO;
import com.example.demo.DAO.PetImageDAO;
import com.example.demo.DAO.StaffDAO;
import com.example.demo.Model.Pet;
import com.example.demo.Model.PetDocument;
import com.example.demo.Model.PetImage;
import com.example.demo.Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private PetDAO petDAO;
    @Autowired
    private PetImageDAO petImageDAO;
    @Autowired
    private PetDocumentDAO petDocumentDAO;

    public StaffService(
            StaffDAO staffDAO,
            PetDAO petDAO,
            PetImageDAO petImageDAO,
            PetDocumentDAO petDocumentDAO
    ){
        this.petDAO = petDAO;
        this.staffDAO = staffDAO;
        this.petImageDAO = petImageDAO;
        this.petDocumentDAO = petDocumentDAO;
    }

    public Optional<Staff> getStaffMemberByEmail(String email){
        return this.staffDAO.getByEmail(email);
    }

    public void addNewPet(Pet pet){
        //pet must have shelterID from the staff that added it
        petDAO.add(pet);
    }

    public void deletePet(int id){
        petDAO.delete(id);
    }

    public void updatePet(Pet updatedPet){
        petDAO.update(updatedPet);
    }

    public List<Pet> getAllPetsOfShelter(int shelterID){
        //assuming a reasonable number of pets in a shelter
        return petDAO.getAllPetsOfShelter(shelterID);
    }

    public void addImage(int petID, String imageLink){
        petImageDAO.add(new PetImage(petID, imageLink));
    }

    public List<PetImage> getImagesOfPet(int petId){
        return petImageDAO.getImagesOfPet(petId);
    }

    public void deleteImage(String imageLink){
        petImageDAO.deleteImage(imageLink);
    }

    public void addDocument(int petID, String documentLink, String type){
        petDocumentDAO.add(new PetDocument(petID, documentLink, type));
    }

    public List<PetDocument> getDocumentsOfPet(int petId){
        return petDocumentDAO.getDocumentsOfPet(petId);
    }

    public void deleteDocument(String documentLink){
        petDocumentDAO.deleteDocument(documentLink);
    }

}
