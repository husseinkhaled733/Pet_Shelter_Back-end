package com.example.demo.DAO;



import com.example.demo.Model.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StaffDAO implements DAO<Staff>{

    private JdbcTemplate template;
    private RowMapper<Staff> staffRowMapper;

    private final String table = "staff";
    private final String staffIdColumn = "staff_id";
    private final String nameColumn = "name";
    private final String roleColumn = "role";
    private final String phoneColumn = "phone";
    private final String emailColumn = "email";
    private final String passwordColumn = "password";
    private final String shelterIdColumn = "shelter_id";
    public StaffDAO(JdbcTemplate template){
        this.template = template;
        this.staffRowMapper = ((rs, rowNum) -> {
            return Staff.builder()
                    .staffID(rs.getInt(staffIdColumn))
                    .name(rs.getString(nameColumn))
                    .role(rs.getString(roleColumn))
                    .phone(rs.getString(phoneColumn))
                    .email(rs.getString(emailColumn))
                    .password(rs.getString(passwordColumn))
                    .shelterID(rs.getInt(shelterIdColumn))
                    .build();
        });
    }

    @Override
    public void add(Staff staff) {
        //for adding normal staff
//        String sql = "insert into staff (name, role, phone, " +
//                "email, password, shelterID) values (?,?,?,?,?,?)";
        String sql = String.format(
                "insert into %s (%s,%s,%s,%s,%s,%s) values (?,?,?,?,?,?)",
                table,
                nameColumn,
                roleColumn,
                phoneColumn,
                emailColumn,
                passwordColumn,
                shelterIdColumn
        );
        template.update(
                sql,
                staff.getName(),
                staff.getRole(),
                staff.getPhone(),
                staff.getEmail(),
                staff.getPassword(),
                staff.getShelterID()
        );
    }

    public void addManager(Staff manager){
//        String sql = "insert into staff (name, role, phone, " +
//                "email, password) values (?,?,?,?,?)";
        String sql = String.format(
                "insert into %s (%s,%s,%s,%s,%s) values (?,?,?,?,?)",
                table,
                nameColumn,
                roleColumn,
                phoneColumn,
                emailColumn,
                passwordColumn
        );
        template.update(
                sql,
                manager.getName(),
                "manager",
                manager.getPhone(),
                manager.getEmail(),
                manager.getPassword()
        );
    }

    @Override
    public Optional<Staff> get(int id) {
//        String sql = "select * from staff where staffID = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                staffIdColumn
        );
        List<Staff> staffList = template.query(sql, staffRowMapper, id);
        return Optional.ofNullable(
                staffList.isEmpty() ? null : staffList.get(0)
        );
    }

    public Optional<Staff> getByEmail(String email){
//        String sql = "select * from staff where email = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                emailColumn
        );
        List<Staff> staffList = template.query(sql, staffRowMapper, email);
        return Optional.ofNullable(
                staffList.isEmpty() ? null : staffList.get(0)
        );
    }

    public List<Staff> getByShelterID(int shelterID){
//        String sql = "select * from staff where shelterID = ?";
        String sql = String.format(
                "select * from %s where %s = ?",
                table,
                shelterIdColumn
        );
        return template.query(sql, staffRowMapper, shelterID);
    }

    @Override
    public void update(Staff updatedStaff) {
//        String sql = "update staff set name = ?, role = ?, " +
//                "phone = ?, email = ?, password = ? where staffID = ?";
        String sql = String.format(
                "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?",
                table,
                nameColumn,
                roleColumn,
                phoneColumn,
                emailColumn,
                passwordColumn,
                staffIdColumn
        );
        template.update(
                sql,
                updatedStaff.getName(),
                updatedStaff.getRole(),
                updatedStaff.getPhone(),
                updatedStaff.getEmail(),
                updatedStaff.getPassword(),
                updatedStaff.getStaffID()
        );
    }

    @Override
    public void delete(int id) {
//        String sql = "delete from staff where staffID = ?";
        String sql = String.format(
                "delete from %s where %s = ?",
                table,
                staffIdColumn
        );
        template.update(sql, id);
    }
}
