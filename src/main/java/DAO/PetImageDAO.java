package DAO;

import Model.PetImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetImageDAO{

    private JdbcTemplate template;
    private RowMapper<PetImage> petImageRowMapper;

    public PetImageDAO(JdbcTemplate template){
        this.template = template;
        this.petImageRowMapper = ((rs, rowNum) -> {
            return new PetImage(
                    rs.getInt("petID"),
                    rs.getString("image")
            );
        });
    }

    public void add(PetImage petImage) {
        String sql = "insert into petImages (image, petID) values (?,?)";
        template.update(sql, petImage.getImageLink(), petImage.getPetID());
    }

    public List<PetImage> getImagesOfPet(int petId){
        String sql = "select * from petImages where petID = ?";
        return template.query(sql, petImageRowMapper, petId);
    }

    public void deleteImage(String imageLink) {
        String sql = "delete from petImages where image = ?";
        template.update(sql, imageLink);
    }
}
