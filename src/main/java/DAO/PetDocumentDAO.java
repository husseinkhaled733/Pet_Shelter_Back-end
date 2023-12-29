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

    private final String table = "petDoc";
    private final String petIdColumn = "pet_id";
    private final String documentColumn = "document";
    private final String typeColumn = "type";


    public PetDocumentDAO(JdbcTemplate template){
        this.template = template;
        this.petDocumentRowMapper = ((rs, rowNum) -> {
            return new PetDocument(
                    rs.getInt(petIdColumn),
                    rs.getString(documentColumn),
                    rs.getString(typeColumn)
            );
        });
    }

    public void add(PetDocument petDocument){
//        String sql = "insert into petDoc (document, petID, type) values (?,?,?)";
        String sql = String.format(
                "insert into %s (%s, %s, %s) values (?,?,?)",
                table,
                documentColumn,
                petIdColumn,
                typeColumn
        );
        template.update(
                sql,
                petDocument.getDocumentLink(),
                petDocument.getPetId(),
                petDocument.getType()
        );
    }

    public List<PetDocument> getDocumentsOfPet(int petID){
//        String sql = "select * from petDoc where petID = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                petIdColumn
        );
        return template.query(sql, petDocumentRowMapper, petID);
    }

    public void deleteDocument(String documentLink){
//        String sql = "delete from petDoc where document = ?";
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                documentColumn
        );
        template.update(sql, documentLink);
    }

}
