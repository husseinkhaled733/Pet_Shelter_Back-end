package DAO;

import Model.Shelter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ShelterDAO implements DAO<Shelter>{

    private JdbcTemplate template;
    private RowMapper<Shelter> shelterRowMapper;

    private final String table = "shelter";
    private final String shelterIdColumn = "shelter_id";
    private final String nameColumn = "name";
    private final String countryColumn = "country";
    private final String cityColumn = "city";
    private final String phoneColumn = "phone";
    private final String emailColumn = "email";
    private final String detailedAddressColumn = "detailed_address";
    private final String managerIdColumn = "manager_id";
    public ShelterDAO(JdbcTemplate template){
        this.template = template;
        shelterRowMapper = ((rs, rowNum) -> {
           return Shelter.builder()
                   .id(rs.getInt(shelterIdColumn))
                   .name(rs.getString(nameColumn))
                   .country(rs.getString(countryColumn))
                   .city(rs.getString(cityColumn))
                   .phone(rs.getString(phoneColumn))
                   .email(rs.getString(emailColumn))
                   .detailedAddress(rs.getString(detailedAddressColumn))
                   .managerID(rs.getInt(managerIdColumn))
                   .build();
        });
    }

    @Override
    public void add(Shelter shelter) {
//        String sql = "insert into shelter (name, country, city, phone," +
//                " email, detailedAddress, managerID) values (?,?,?,?,?,?,?)";
        String sql = String.format(
                "insert into %s (%s,%s,%s,%s,%s,%s,%s) values (?,?,?,?,?,?,?)",
                table,
                nameColumn,
                countryColumn,
                cityColumn,
                phoneColumn,
                emailColumn,
                detailedAddressColumn,
                managerIdColumn
        );

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
//        String sql = "select * from shelter where shelterID = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                shelterIdColumn
        );
        List<Shelter> shelterList = template.query(sql, shelterRowMapper, id);
        return Optional.ofNullable(
                shelterList.isEmpty() ? null : shelterList.get(0)
        );
    }

    @Override
    public void update(Shelter updatedShelter) {
//        String sql = "update shelter set name = ?, country = ?, city = ?, " +
//                "phone = ?, email = ?, detailedAddress = ?" +
//                "where shelterID = ?";
        String sql = String.format(
                "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                table,
                nameColumn,
                countryColumn,
                cityColumn,
                phoneColumn,
                emailColumn,
                detailedAddressColumn,
                shelterIdColumn
        );
        template.update(
                sql,
                updatedShelter.getName(),
                updatedShelter.getCountry(),
                updatedShelter.getCity(),
                updatedShelter.getPhone(),
                updatedShelter.getEmail(),
                updatedShelter.getDetailedAddress(),
                updatedShelter.getId()
        );
    }

    @Override
    public void delete(int id) {
//        String sql = "delete from shelter where shelterID = ?";
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                shelterIdColumn
        );
        template.update(sql, id);
    }

    public Optional<Shelter> getShelterByEmail(String email){
//        String sql = "select * from shelter where email = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                emailColumn
        );
        List<Shelter> shelterList = template.query(sql, shelterRowMapper, email);
        return Optional.ofNullable(
                shelterList.isEmpty() ? null : shelterList.get(0)
        );
    }
}
