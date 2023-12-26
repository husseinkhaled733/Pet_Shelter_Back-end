package Model;

public class ShelterBuilder {
    private final Shelter shelter;

    public ShelterBuilder(){
        this.shelter = new Shelter();
    }

    public ShelterBuilder setID(int id){
        this.shelter.setId(id);
        return this;
    }

    public ShelterBuilder setName(String name){
        this.shelter.setName(name);
        return this;
    }

    public ShelterBuilder setCountry(String country){
        this.shelter.setCountry(country);
        return this;
    }

    public ShelterBuilder setCity(String city){
        this.shelter.setCity(city);
        return this;
    }

    public ShelterBuilder setPhone(String phone){
        this.shelter.setPhone(phone);
        return this;
    }

    public ShelterBuilder setEmail(String email){
        this.shelter.setEmail(email);
        return this;
    }

    public ShelterBuilder setDetailedAddress(String address){
        this.shelter.setDetailedAddress(address);
        return this;
    }

    public ShelterBuilder setManagerID(int managerID){
        this.shelter.setManagerID(managerID);
        return this;
    }

    public Shelter get(){
        return this.shelter;
    }
}
