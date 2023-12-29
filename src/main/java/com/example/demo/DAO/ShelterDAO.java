package com.example.demo.DAO;

import com.example.demo.Model.Shelter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShelterDAO implements DAO<Shelter>{

    private JdbcTemplate template;
    private RowMapper<Shelter> shelterRowMapper;

    public ShelterDAO(JdbcTemplate template){
        this.template = template;
        shelterRowMapper = ((rs, rowNum) -> Shelter.builder()
                .id(rs.getInt("shelter_id"))
                .name(rs.getString("name"))
                .country(rs.getString("country"))
                .city(rs.getString("city"))
                .phone(rs.getString("phone"))
                .email(rs.getString("email"))
                .detailedAddress(rs.getString("detailed_address"))
                .managerId(rs.getInt(rs.getInt("manager_id")))
                .build());
    }

    @Override
    public void add(Shelter shelter) {
        String sql = "insert into shelter (name, country, city, phone," +
                " email, detailed_address, manager_id) values (?,?,?,?,?,?,?)";
        //shelterID is auto-Incremented
        //TODO managerID
        template.update(
                sql,
                shelter.getName(),
                shelter.getCountry(),
                shelter.getCity(),
                shelter.getPhone(),
                shelter.getEmail(),
                shelter.getDetailedAddress(),
                shelter.getManagerId()
        );
    }

    @Override
    public Optional<Shelter> get(int id) {
        String sql = "select * from shelter where shelter_id = ?";
        return Optional.ofNullable(template
                .queryForObject(sql, shelterRowMapper, id));
    }

    @Override
    public void update(Shelter updatedShelter) {
        String sql = "update shelter set name = ?, country = ?, city = ?, " +
                "phone = ?, email = ?, detailed_address = ?" +
//                ", managerID = ? " +
                "where shelter_id = ?";
        template.update(
                sql,
                updatedShelter.getName(),
                updatedShelter.getCountry(),
                updatedShelter.getCity(),
                updatedShelter.getPhone(),
                updatedShelter.getEmail(),
                updatedShelter.getDetailedAddress(),
                //TODO managerID
//                updatedShelter.getManagerID(),
                updatedShelter.getId()
        );
    }

    @Override
    public void delete(int id) {
        String sql = "delete from shelter where shelter_id = ?";
        template.update(sql, id);
    }
}
