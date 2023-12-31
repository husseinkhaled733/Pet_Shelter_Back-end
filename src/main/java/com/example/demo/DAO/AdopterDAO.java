package com.example.demo.DAO;

import com.example.demo.Model.Adopter;
import com.example.demo.Model.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AdopterDAO implements DAO<Adopter>{
    private JdbcTemplate template;
    private RowMapper<Adopter> adopterRowMapper;

    private final String table = "adopter";
    private final String adopterIdColumn = "adopter_id";
    private final String nameColumn = "name";
    private final String phoneColumn = "phone";
    private final String emailColumn = "email";
    private final String passwordColumn = "password";

    public AdopterDAO(JdbcTemplate template){
        this.template = template;
        this.adopterRowMapper = ((rs, rowNum) -> {
           return Adopter.builder()
                   .adopterId(rs.getInt(adopterIdColumn))
                   .name(rs.getString(nameColumn))
                   .phone(rs.getString(phoneColumn))
                   .email(rs.getString(emailColumn))
                   .password(rs.getString(passwordColumn))
                   .build();
        });
    }

    @Override
    public void add(Adopter adopter) {
        String sql = String.format(
                "insert into %s (%s,%s,%s,%s) values (?,?,?,?)",
                table,
                nameColumn,
                phoneColumn,
                emailColumn,
                passwordColumn
        );
        template.update(
                sql,
                adopter.getName(),
                adopter.getPhone(),
                adopter.getEmail(),
                adopter.getPassword()
        );
    }

    @Override
    public Optional<Adopter> get(int id) {
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                adopterIdColumn
        );
        List<Adopter> staffList = template.query(sql, adopterRowMapper, id);
        return Optional.ofNullable(
                staffList.isEmpty() ? null : staffList.get(0)
        );
    }

    @Override
    public void update(Adopter updatedAdopter) {
        String sql = String.format(
                "update %s set %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                table,
                nameColumn,
                phoneColumn,
                emailColumn,
                passwordColumn,
                adopterIdColumn
        );
        template.update(
                sql,
                updatedAdopter.getName(),
                updatedAdopter.getPhone(),
                updatedAdopter.getEmail(),
                updatedAdopter.getPassword(),
                updatedAdopter.getAdopterId()
        );
    }

    @Override
    public void delete(int id) {
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                adopterIdColumn
        );
        template.update(sql, id);
    }

    public void delete(String email) {
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                emailColumn
        );
        template.update(sql, email);
    }
    //search - filter - view application - submit application
    public Optional<Adopter> getByEmail(String email){
//        String sql = "select * from staff where email = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                emailColumn
        );
        List<Adopter> adopterList = template.query(sql, adopterRowMapper, email);

        return Optional.ofNullable(
                adopterList.isEmpty() ? null : adopterList.get(0)
        );

    }


}
