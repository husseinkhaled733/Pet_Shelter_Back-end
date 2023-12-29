package com.example.demo.Services;


import com.example.demo.DAO.SecDAO;
import com.example.demo.DAO.ShelterDAO;
import com.example.demo.DAO.StaffDAO;
import com.example.demo.Model.Shelter;
import com.example.demo.Model.Staff;
import org.mindrot.jbcrypt.BCrypt;
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
    @Autowired
    private SecDAO secDAO;

    public ManagerService(ShelterDAO shelterDAO, StaffDAO staffDAO, SecDAO secDAO){
        this.shelterDAO = shelterDAO;
        this.staffDAO = staffDAO;
        this.secDAO = secDAO;
    }

    public void addNewManager(Staff manager){
        manager.setPassword(BCrypt.hashpw(manager.getPassword(), BCrypt.gensalt()));
        staffDAO.addManager(manager);
        secDAO.addManager(manager);
    }

    public void addNewShelter(Shelter shelter, String managerEmail){
        Optional<Staff> managerContainer = staffDAO.getByEmail(managerEmail);
        if(managerContainer.isEmpty()){
            throw new RuntimeException("Manager not found");
        }
        shelter.setManagerID(managerContainer.get().getStaffID());
        shelterDAO.add(shelter);
        //shelterID of manager is assigned by a trigger
    }

    public Optional<Shelter> getShelterOfStaff(Staff staff){
        //use only if shelterID attribute is available and valid
        return shelterDAO.get(staff.getShelterID());
    }

    public Optional<Shelter> getShelterOfStaff(int staffId){
        Optional<Staff> staffContainer = staffDAO.get(staffId);
        if(staffContainer.isEmpty()){
            throw new RuntimeException("Cannot find manager");
        }
        return shelterDAO.get(staffContainer.get().getShelterID());
    }

    public boolean addNewStaff(Staff staff, String managerEmail){
        Optional<Staff> managerContainer = staffDAO.getByEmail(managerEmail);
        if(managerContainer.isEmpty()){
            throw new RuntimeException("Manager not found");
        }
        Optional<Shelter> shelterContainer = getShelterOfStaff(managerContainer.get().getStaffID());
        if(shelterContainer.isEmpty()){return false;}
        staff.setShelterID(shelterContainer.get().getId());
        staff.setPassword(BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt()));
        secDAO.addStaff(staff);
        staffDAO.add(staff);
        return true;
    }

    public List<Staff> getAllStaffOfShelter(String shelterEmail){
        Optional<Shelter> shelterContainer = shelterDAO.getShelterByEmail(shelterEmail);
        if(shelterContainer.isEmpty()){
            throw new RuntimeException("Shelter not found");
        }
        return staffDAO.getByShelterID(shelterContainer.get().getId());
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
        shelter.setManagerID(newManager.getStaffID());
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

    public Optional<Staff> getStaffByEmail(String email){
        return staffDAO.getByEmail(email);
    }

    public void deleteStaffByEmail(String staffEmail){
        staffDAO.delete(staffEmail);
    }
}
