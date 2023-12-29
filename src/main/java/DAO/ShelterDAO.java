package DAO;

import Model.Shelter;
import Model.ShelterBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ShelterDAO implements DAO<Shelter>{

    private JdbcTemplate template;
    private RowMapper<Shelter> shelterRowMapper;

    public ShelterDAO(JdbcTemplate template){
        this.template = template;
        shelterRowMapper = ((rs, rowNum) -> {
           return new ShelterBuilder()
                   .setID(rs.getInt("shelterID"))
                   .setName(rs.getString("name"))
                   .setCountry(rs.getString("country"))
                   .setCity(rs.getString("city"))
                   .setPhone(rs.getString("phone"))
                   .setEmail(rs.getString("email"))
                   .setDetailedAddress(rs.getString("detailedAddress"))
                   .setManagerID(rs.getInt("managerID"))
                   .get();
        });
    }

    @Override
    public void add(Shelter shelter) {
        String sql = "insert into shelter (name, country, city, phone," +
                " email, detailedAddress, managerID) values (?,?,?,?,?,?,?)";
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
                shelter.getManagerID()
        );
    }

    @Override
    public Optional<Shelter> get(int id) {
        String sql = "select * from shelter where shelterID = ?";
        List<Shelter> shelterList = template.query(sql, shelterRowMapper, id);
        return Optional.ofNullable(
                shelterList.isEmpty() ? null : shelterList.get(0)
        );
    }

    @Override
    public void update(Shelter updatedShelter) {
        String sql = "update shelter set name = ?, country = ?, city = ?, " +
                "phone = ?, email = ?, detailedAddress = ?" +
//                ", managerID = ? " +
                "where shelterID = ?";
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
        String sql = "delete from shelter where shelterID = ?";
        template.update(sql, id);
    }

    public Optional<Shelter> getShelterByEmail(String email){
        String sql = "select * from shelter where email = ?";
        List<Shelter> shelterList = template.query(sql, shelterRowMapper, email);
        return Optional.ofNullable(
                shelterList.isEmpty() ? null : shelterList.get(0)
        );
    }
}
