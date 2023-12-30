package com.example.demo.DAO;

import com.example.demo.Model.Application;
import com.example.demo.Model.Shelter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Component
public class ApplicationDAO{
    private JdbcTemplate template;
    private RowMapper<Application> applicationRowMapper;

    private final String table = "application";
    private final String petTable = "pet";
    private final String adopterIdColumn = "adopter_id";
    private final String petIdColumn = "pet_id";
    private final String statusColumn = "status";

    public final String PENDING = "pending";
    public final String ACCEPTED = "accepted";
    public final String REJECTED = "rejected";

    public ApplicationDAO(JdbcTemplate template){
        this.template = template;
        this.applicationRowMapper = ((rs, rowNum) -> {
            return new Application(
                    rs.getInt(adopterIdColumn),
                    rs.getInt(petIdColumn),
                    rs.getString(statusColumn)
            );
        });
    }

    public void addNewApplication(int adopterId, int petId){
        String sql = String.format(
                "insert into %s (%s,%s,%s) values (?,?,?)",
                table,
                adopterIdColumn,
                petIdColumn,
                statusColumn
        );
        template.update(sql, adopterId, petId, PENDING);
    }

    public List<Application> getApplicationsOfAdopter(int adopterId){
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                adopterIdColumn
        );
        return template.query(sql, applicationRowMapper, adopterId);
    }

    public List<Application> getApplicationsOfPet(int petId){
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                petIdColumn
        );
        return template.query(sql, applicationRowMapper, petId);
    }

    public Optional<Application> getApplication(int adopterId, int petId){
        String sql = String.format(
                "select * from %s where %s = ? and %s = ?",
                table,
                adopterIdColumn,
                petIdColumn
        );
        List<Application> appList = template.query(
                sql,
                applicationRowMapper,
                adopterId,
                petId
        );
        return Optional.ofNullable(appList.isEmpty() ? null : appList.get(0));
    }

    public List<Application> getAllApplicationsOfShelter(Shelter shelter){
//        String sql = "select adopter_id, pet_id from " +
//                "pet join application on pet.pet_id = application.pet_id";
        String sql = String.format(
                "select %s.%s,%s.%s from %s join %s on %s.%s = %s.%s",
                table,
                adopterIdColumn,
                table,
                petIdColumn,
                petTable,
                table,
                petTable,
                petIdColumn,
                table,
                petIdColumn
        );
        return template.query(sql, applicationRowMapper);
    }

    public void setApplicationStatus(int adopterId, int petId, String newStatus){
        String sql = String.format(
                "update %s set %s = ? where %s = ? and %s = ?",
                table,
                statusColumn,
                adopterIdColumn,
                petIdColumn
        );
        template.update(sql, newStatus, adopterId, petId);
    }
}
