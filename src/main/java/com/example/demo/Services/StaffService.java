package com.example.demo.Services;


import com.example.demo.DAO.*;
import com.example.demo.Model.*;
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
    @Autowired
    private ApplicationDAO applicationDAO;
    @Autowired
    private ShelterDAO shelterDAO;
    @Autowired
    private AdopterDAO adopterDAO;

    public StaffService(
            StaffDAO staffDAO,
            PetDAO petDAO,
            PetImageDAO petImageDAO,
            PetDocumentDAO petDocumentDAO,
            ApplicationDAO applicationDAO,
            ShelterDAO shelterDAO,
            AdopterDAO adopterDAO
    ){
        this.petDAO = petDAO;
        this.staffDAO = staffDAO;
        this.petImageDAO = petImageDAO;
        this.petDocumentDAO = petDocumentDAO;
        this.applicationDAO = applicationDAO;
        this.shelterDAO = shelterDAO;
        this.adopterDAO = adopterDAO;
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

    public Optional<Pet> getPetById(int petId){
        return petDAO.get(petId);
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

    public Optional<Shelter> getShelter(int shelterId){
        return shelterDAO.get(shelterId);
    }
    public List<Application> getAllApplicationsOfShelter(Shelter shelter){
        return applicationDAO.getAllApplicationsOfShelter(shelter);
    }

    public Optional<Adopter> getAdopterById(int adopterId){
        return adopterDAO.get(adopterId);
    }

    public Optional<Adopter> getAdopterByEmail(String adopterEmail){
        return adopterDAO.getByEmail(adopterEmail);
    }

    public Optional<Application> getApplication(int adopterId, int petId){
        return applicationDAO.getApplication(adopterId, petId);
    }

    public void setApplicationStatus(Application application, String newStatus){
        applicationDAO.setApplicationStatus(
                application.getAdopterId(),
                application.getPetId(),
                newStatus
        );
    }
}
