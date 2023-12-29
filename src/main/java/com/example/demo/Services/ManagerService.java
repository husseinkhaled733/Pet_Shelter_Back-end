package com.example.demo.Services;

import com.example.demo.DAO.SecDAO;
import com.example.demo.DAO.ShelterDAO;
import com.example.demo.DAO.StaffDAO;
import com.example.demo.DTOs.StaffDTO;
import com.example.demo.Model.Shelter;
import com.example.demo.Model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;


import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private ShelterDAO shelterDAO;
    @Autowired
    private StaffDAO staffDAO;


    @Autowired
    SecDAO secDAO;



    public void addNewManager(StaffDTO staffDTO){
        Staff staff = Staff.builder()
                .name(staffDTO.getName())
                .role("manager")
                .phone(staffDTO.getPhone())
                .email(staffDTO.getEmail())
                .password(BCrypt.hashpw(staffDTO.getPassword(), BCrypt.gensalt()))
                .build();

        staffDAO.addManager(staff);
        secDAO.addManager(staff);
    }

    public void addNewShelter(Shelter shelter, Staff manager){
        shelter.setManagerId(manager.getStaffId());
        shelterDAO.add(shelter);
        //shelterID of manager is assigned by a trigger
    }

    public Shelter getShelterOfStaff(Staff staff){
        return shelterDAO.get(staff.getShelterId()).get();
    }

    public void addNewStaff(Staff staff, Staff manager){
        staff.setShelterId(getShelterOfStaff(manager).getId());
        staffDAO.add(staff);
    }

    public List<Staff> getAllStaffOfShelter(Shelter shelter){
        return staffDAO.getByShelterID(shelter.getId());
    }

    public Staff getManagerOfShelter(Shelter shelter){
        return staffDAO.get(shelter.getManagerId()).get();
    }

    public void updateStaffRole(Staff staff, String newRole){
        staff.setRole(newRole);
        staffDAO.update(staff);
    }
}
