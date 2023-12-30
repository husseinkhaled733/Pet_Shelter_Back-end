//package com.example.demo.DAO;
//
//
//import com.example.demo.Controllers.SearchRequestWrapper;
//import com.example.demo.Model.Pet;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import java.time.ZoneId;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PetDAO implements DAO<Pet> {
//
//    private JdbcTemplate template;
//    private RowMapper<Pet> petRowMapper;
//
//    private final String table = "pet";
//    private final String petIdColumn = "pet_id";
//    private final String nameColumn = "name";
//    private final String dateOfBirthColumn = "date_of_birth";
//    private final String genderColumn = "gender";
//
//    private final String healthStatusColumn = "health_status";
//    private final String speciesColumn = "species";
//    private final String breedColumn = "breed";
//    private final String behaviorColumn = "behavior";
//    private final String descriptionColumn = "description";
//    private final String shelterIDColumn = "shelter_id";
//
//
//    public PetDAO(JdbcTemplate template){
//        this.template = template;
//        petRowMapper = ((rs, rowNum) -> {
//            return Pet.builder()
//                    .petID(rs.getInt(petIdColumn))
//                    .name(rs.getString(nameColumn))
//                    .gender(rs.getBoolean(genderColumn))
//                    .healthStatus(rs.getString(healthStatusColumn))
//                    .dateOfBirth(
//                            rs.getDate(dateOfBirthColumn)
//                                    .toInstant()
//                                    .atZone(ZoneId.systemDefault())
//                                    .toLocalDate()
//                    )
//                    .species(rs.getString(speciesColumn))
//                    .breed(rs.getString(breedColumn))
//                    .behavior(rs.getString(behaviorColumn))
//                    .description(rs.getString(descriptionColumn))
//                    .shelterID(rs.getInt(shelterIDColumn))
//                    .build();
//        });
//    }
//
//    @Override
//    public void add(Pet pet) {
////        String sql = "insert into pet (name, gender, healthStatus, dateOfBirth, " +
////                "species, breed, behavior, description, shelterID) " +
////                "values (?,?,?,?,?,?,?,?,?)";
////
//        String sql = String.format(
//                "insert into %s (%s, %s, %s, %s, %s, %s, %s, %s, %s) values (?,?,?,?,?,?,?,?,?)",
//                table,
//                nameColumn,
//                genderColumn,
//                healthStatusColumn,
//                dateOfBirthColumn,
//                speciesColumn,
//                breedColumn,
//                behaviorColumn,
//                descriptionColumn,
//                shelterIDColumn
//        );
//        //petID is auto-incremented
//        template.update(
//                sql,
//                pet.getName(),
//                pet.isGender(),
//                pet.getHealthStatus(),
//                pet.getDateOfBirth(),
//                pet.getSpecies(),
//                pet.getBreed(),
//                pet.getBehavior(),
//                pet.getDescription(),
//                pet.getShelterID()
//        );
//    }
//
//    @Override
//    public Optional<Pet> get(int id) {
////        String sql = "select * from pet where petID = ?";
//        String sql = String.format(
//                "select * from %s where %s = ?",
//                table,
//                petIdColumn
//        );
//        List<Pet> petList = template.query(sql, petRowMapper, id);
//        return Optional.ofNullable(
//                petList.isEmpty() ? null : petList.get(0)
//        );
//    }
//
//    @Override
//    public void update(Pet updatedPet) {
////        String sql = "update pet set name = ?, gender = ?, healthStatus = ?," +
////                " dateOfBirth = ?, species = ?, breed = ?, behavior = ?," +
////                " description = ? where petID = ?";
//        String sql = String.format(
//                "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
//                table,
//                nameColumn,
//                genderColumn,
//                healthStatusColumn,
//                dateOfBirthColumn,
//                speciesColumn,
//                breedColumn,
//                behaviorColumn,
//                descriptionColumn,
//                petIdColumn
//        );
//        template.update(
//                sql,
//                updatedPet.getName(),
//                updatedPet.isGender(),
//                updatedPet.getHealthStatus(),
//                updatedPet.getDateOfBirth(),
//                updatedPet.getSpecies(),
//                updatedPet.getBreed(),
//                updatedPet.getBehavior(),
//                updatedPet.getDescription(),
//                updatedPet.getPetID()
//        );
//    }
//
//    @Override
//    public void delete(int id) {
////        String sql = "delete from pet where petID = ?";
//        String sql = String.format(
//                "delete from %s where %s = ?",
//                table,
//                petIdColumn
//        );
//        template.update(sql, id);
//    }
//
//    public List<Pet> getAllPetsOfShelter(int shelterID){
////        String sql = "select * from pet where shelterID = ?";
//        String sql = String.format(
//                "select * from %s where %s = ?",
//                table,
//                shelterIDColumn
//        );
//        return template.query(sql, petRowMapper, shelterID);
//    }
//
//
//    public List<Pet> getAllPetsWithCriteria(SearchRequestWrapper searchRequestWrapper){
//
//        String sqlQuert = "SELECT shelter.shelter_id,\n" +
//                "    shelter.name AS shelter_name,\n" +
//                "    pet.pet_id,\n" +
//                "    pet.name AS pet_name,\n" +
//                "    pet.species,\n" +
//                "    pet.breed,\n" +
//                "    TIMESTAMPDIFF(YEAR, pet.date_of_birth, CURDATE()) AS age_in_years\n" +
//                "FROM\n" +
//                "    shelter\n" +
//                "JOIN\n" +
//                "    pet ON shelter.shelter_id = pet.shelter_id\n" +
//                "WHERE\n" +
//                "    shelter.name LIKE '%your_shelter_name%' -- Replace with the desired shelter name\n" +
//                "    AND pet.species LIKE '%your_species%' -- Replace with the desired species\n" +
//                "    AND pet.breed LIKE '%your_breed%' -- Replace with the desired breed\n" +
//                "    AND TIMESTAMPDIFF(YEAR, pet.date_of_birth, CURDATE()) <= your_max_age -- Replace with the maximum desired age\n" +
//                "    AND TIMESTAMPDIFF(YEAR, pet.date_of_birth, CURDATE()) >= your_min_age; -- Replace with the minimum desired age\n"
//        String sql = String.format(
//                "select * from %s where %s like ? and %s like ? and %s like ? and %s like ? and %s like ?",
//                table,
//                nameColumn,
//                speciesColumn,
//                breedColumn,
//                behaviorColumn,
//                healthStatusColumn
//        );
//        return template.query(sql, petRowMapper, name, species, breed, behavior, healthStatus);
//    }
//}
