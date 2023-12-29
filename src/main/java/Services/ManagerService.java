package Services;

import DAO.ShelterDAO;
import DAO.StaffDAO;
import Model.Shelter;
import Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ShelterDAO shelterDAO;
    @Autowired
    private StaffDAO staffDAO;

    public ManagerService(ShelterDAO shelterDAO, StaffDAO staffDAO){
        this.shelterDAO = shelterDAO;
        this.staffDAO = staffDAO;
    }

    public void addNewManager(Staff manager){
        staffDAO.addManager(manager);
    }

    public void addNewShelter(Shelter shelter, Staff manager){
        shelter.setManagerID(manager.getId());
        shelterDAO.add(shelter);
        //shelterID of manager is assigned by a trigger
    }

    public Optional<Shelter> getShelterOfStaff(Staff staff){
        //use only if shelterID attribute is available and valid
        return shelterDAO.get(staff.getShelterID());
    }

    public Optional<Shelter> getShelterOfStaff(int staffId){
        return shelterDAO.get(staffDAO.get(staffId).get().getShelterID());
    }

    public boolean addNewStaff(Staff staff, Staff manager){
        Optional<Shelter> shelterContainer = getShelterOfStaff(manager.getId());
        if(shelterContainer.isEmpty()){return false;}
        staff.setShelterID(shelterContainer.get().getId());
        staffDAO.add(staff);
        return true;
    }

    public List<Staff> getAllStaffOfShelter(Shelter shelter){
        return staffDAO.getByShelterID(shelter.getId());
    }

    public Staff getManagerOfShelter(Shelter shelter){
        //any shelter must have a manager
        return staffDAO.get(shelter.getManagerID()).get();
    }

    public void updateStaffRole(Staff staff, String newRole){
        staff.setRole(newRole);
        staffDAO.update(staff);
    }

    public boolean changeManager(Staff currentManager, Staff newManager, String newRoleOfCurrentManager){
        if(currentManager.getShelterID()
                != newManager.getShelterID()){
            throw new RuntimeException("The two members do not work for the same shelter");
        }
        updateStaffRole(currentManager, newRoleOfCurrentManager);
        updateStaffRole(newManager, "manager");
        //TODO refactor string literal "manager"
        Optional<Shelter> shelterContainer = getShelterOfStaff(currentManager);
        if(shelterContainer.isEmpty()){return false;}
        Shelter shelter = shelterContainer.get();
        shelter.setManagerID(newManager.getId());
        shelterDAO.update(shelter);
        return true;
    }

    public boolean emailExistsInStaff(String email){
        return staffDAO.getByEmail(email).isPresent();
    }

    public boolean staffExists(int id){
        return staffDAO.get(id).isPresent();
    }

    public boolean emailExistsInShelters(String email){
        return shelterDAO.getShelterByEmail(email).isPresent();
    }

    public Optional<Shelter> getShelterById(int shelterId){
        return shelterDAO.get(shelterId);
    }

    public Optional<Staff> getStaffById(int staffId){
        return staffDAO.get(staffId);
    }


}
