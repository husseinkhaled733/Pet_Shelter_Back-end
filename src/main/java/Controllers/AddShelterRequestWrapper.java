package Controllers;

import Model.Shelter;
import Model.Staff;

public class AddShelterRequestWrapper {
    private Shelter shelter;
    private Staff manager;

    public AddShelterRequestWrapper(Shelter shelter, Staff manager){
        this.shelter = shelter;
        this.manager = manager;
    }

    public Staff getManager() {
        return manager;
    }

    public Shelter getShelter(){
        return shelter;
    }
}
