package Services;

import DAO.ShelterDAO;
import DAO.StaffDAO;
import Model.Shelter;
import Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Service {

    @Autowired
    private ShelterDAO shelterDAO;
    @Autowired
    private StaffDAO staffDAO;

    public Service(ShelterDAO shelterDAO, StaffDAO staffDAO){
        this.shelterDAO = shelterDAO;
        this.staffDAO = staffDAO;
    }

    public void addNewManager(Staff staff){
        staffDAO.addManager(staff);
    }

    public void addNewShelter(Shelter shelter, Staff manager){
        shelter.setManagerID(manager.getId());
        shelterDAO.add(shelter);
        //shelterID of manager is assigned by a trigger
    }

    public Shelter getShelterOfStaff(Staff staff){
        return shelterDAO.get(staff.getShelterID()).get();
    }

    public void addNewStaff(Staff staff, Staff manager){
        staff.setShelterID(getShelterOfStaff(manager).getId());
        staffDAO.add(staff);
    }

    public List<Staff> getAllStaffOfShelter(Shelter shelter){
        return staffDAO.getByShelterID(shelter.getId());
    }

    public Staff getManagerOfShelter(Shelter shelter){
        return staffDAO.get(shelter.getManagerID()).get();
    }

    public void updateStaffRole(Staff staff, String newRole){
        staff.setRole(newRole);
        staffDAO.update(staff);
    }

    public void changeManager(Staff currentManager, Staff newManager, String newRoleOfCurrentManager){
        if(currentManager.getShelterID()
                != newManager.getShelterID()){
            throw new RuntimeException("The two members do not work for the same shelter");
        }
        updateStaffRole(currentManager, newRoleOfCurrentManager);
        updateStaffRole(newManager, "manager");
        //TODO refactor string literal "manager"
        Shelter shelter = getShelterOfStaff(currentManager);
        shelter.setManagerID(newManager.getId());
        shelterDAO.update(shelter);
    }


}
