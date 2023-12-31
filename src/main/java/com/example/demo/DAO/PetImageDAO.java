package com.example.demo.DAO;


import com.example.demo.Model.PetImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetImageDAO{

    private JdbcTemplate template;
    private RowMapper<PetImage> petImageRowMapper;

    private final String table = "petimages";
    private final String petIdColumn = "pet_id";
    private final String imageColumn = "image";

    public PetImageDAO(JdbcTemplate template){
        this.template = template;
        this.petImageRowMapper = ((rs, rowNum) -> {
            return new PetImage(
                    rs.getInt(petIdColumn),
                    rs.getString(imageColumn)
            );
        });
    }

    public void add(PetImage petImage) {
//        String sql = "insert into petImages (image, petID) values (?,?)";
        String sql = String.format(
                "insert into %s (%s, %s) values (?,?)",
                table,
                imageColumn,
                petIdColumn
        );
        template.update(sql, petImage.getImageLink(), petImage.getPetID());
    }

    public List<PetImage> getImagesOfPet(int petId){
//        String sql = "select * from petImages where petID = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                petIdColumn
        );
        return template.query(sql, petImageRowMapper, petId);
    }

    public void deleteImage(String imageLink) {
//        String sql = "delete from petImages where image = ?";
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                imageColumn
        );
        template.update(sql, imageLink);
    }
}
