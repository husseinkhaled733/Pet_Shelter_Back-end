package DAO;

import Model.PetDocument;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetDocumentDAO {

    private JdbcTemplate template;
    private RowMapper<PetDocument> petDocumentRowMapper;

    public PetDocumentDAO(JdbcTemplate template){
        this.template = template;
        this.petDocumentRowMapper = ((rs, rowNum) -> {
            return new PetDocument(
                    rs.getInt("petID"),
                    rs.getString("document"),
                    rs.getString("type")
            );
        });
    }

    public void add(PetDocument petDocument){
        String sql = "insert into petDoc (document, petID, type) values (?,?,?)";
        template.update(
                sql,
                petDocument.getDocumentLink(),
                petDocument.getPetId(),
                petDocument.getType()
        );
    }

    public List<PetDocument> getDocumentsOfPet(int petID){
        String sql = "select * from petDoc where petID = ?";
        return template.query(sql, petDocumentRowMapper, petID);
    }

    public void deleteDocument(String documentLink){
        String sql = "delete from petDoc where document = ?";
        template.update(sql, documentLink);
    }

}
