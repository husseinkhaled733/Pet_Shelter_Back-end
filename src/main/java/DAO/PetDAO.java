package DAO;

import Model.Pet;
import Model.PetBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class PetDAO implements DAO<Pet> {

    private JdbcTemplate template;
    private RowMapper<Pet> petRowMapper;

    public PetDAO(JdbcTemplate template){
        this.template = template;
        petRowMapper = ((rs, rowNum) -> {
            return new PetBuilder()
                    .petID(rs.getInt("petID"))
                    .name(rs.getString("name"))
                    .gender(rs.getBoolean("gender"))
                    .healthStatus(rs.getString("healthStatus"))
                    .dateOfBirth(
                            rs.getDate("dateOfBirth")
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                    )
                    .species(rs.getString("species"))
                    .breed(rs.getString("breed"))
                    .behavior(rs.getString("behavior"))
                    .description(rs.getString("description"))
                    .shelterID(rs.getInt("shelterID"))
                    .get();
        });
    }

    @Override
    public void add(Pet pet) {
        String sql = "insert into pet (name, gender, healthStatus, dateOfBirth, " +
                "species, breed, behavior, description, shelterID) " +
                "values (?,?,?,?,?,?,?,?,?)";
        //petID is auto-incremented
        template.update(
                sql,
                pet.getName(),
                pet.getGender(),
                pet.getHealthStatus(),
                pet.getDateOfBirth(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getBehavior(),
                pet.getDescription(),
                pet.getShelterID()
        );
    }

    @Override
    public Optional<Pet> get(int id) {
        String sql = "select * from pet where petID = ?";
        List<Pet> petList = template.query(sql, petRowMapper, id);
        return Optional.ofNullable(
                petList.isEmpty() ? null : petList.get(0)
        );
    }

    @Override
    public void update(Pet updatedPet) {
        String sql = "update pet set name = ?, gender = ?, healthStatus = ?," +
                " dateOfBirth = ?, species = ?, breed = ?, behavior = ?," +
                " description = ? where petID = ?";
        template.update(
                sql,
                updatedPet.getName(),
                updatedPet.getGender(),
                updatedPet.getHealthStatus(),
                updatedPet.getDateOfBirth(),
                updatedPet.getSpecies(),
                updatedPet.getBreed(),
                updatedPet.getBehavior(),
                updatedPet.getDescription(),
                updatedPet.getPetID()
        );
    }

    @Override
    public void delete(int id) {
        String sql = "delete from pet where petID = ?";
        template.update(sql, id);
    }

    public List<Pet> getAllPetsOfShelter(int shelterID){
        String sql = "select * from pet where shelterID = ?";
        return template.query(sql, petRowMapper, shelterID);
    }
}
