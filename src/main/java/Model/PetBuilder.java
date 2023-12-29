package Model;

import java.time.LocalDate;

public class PetBuilder {
    private Pet pet;

    public PetBuilder(){
        this.pet = new Pet();
    }

    public PetBuilder petID(int petID){
        pet.setPetID(petID);
        return this;
    }

    public PetBuilder name(String name){
        pet.setName(name);
        return this;
    }

    public PetBuilder gender(boolean gender){
        pet.setGender(gender);
        return this;
    }

    public PetBuilder healthStatus(String healthStatus){
        pet.setHealthStatus(healthStatus);
        return this;
    }

    public PetBuilder dateOfBirth(LocalDate date){
        pet.setDateOfBirth(date);
        return this;
    }

    public PetBuilder species(String species){
        pet.setSpecies(species);
        return this;
    }

    public PetBuilder breed(String breed){
        pet.setBreed(breed);
        return this;
    }

    public PetBuilder behavior(String behavior){
        pet.setBehavior(behavior);
        return this;
    }

    public PetBuilder description(String description){
        pet.setDescription(description);
        return this;
    }

    public PetBuilder shelterID(int shelterID){
        pet.setShelterID(shelterID);
        return this;
    }

    public Pet get(){
        return pet;
    }
}
