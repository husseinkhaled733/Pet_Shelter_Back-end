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

    public StaffDAO(JdbcTemplate template){
        this.template = template;
        this.staffRowMapper = ((rs, rowNum) ->  Staff.builder()
                .staffId(rs.getInt("staff_id"))
                .name(rs.getString("name"))
                .role(rs.getString("role"))
                .password(rs.getString("phone"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .shelterId(rs.getInt("shelter_id"))
                .build());
    }

    @Override
    public void add(Staff staff) {
        //for adding normal staff
        String sql = "insert into staff (name, role, phone, " +
                "email, password, shelter_id) values (?,?,?,?,?,?)";
        template.update(
                sql,
                staff.getName(),
                staff.getRole(),
                staff.getPhone(),
                staff.getEmail(),
                staff.getPassword(),
                staff.getShelterId()
        );
    }

    public void addManager(Staff manager){
        String sql = "insert into staff (name, role, phone, " +
                "email, password) values (?,?,?,?,?)";
        template.update(
                sql,
                manager.getName(),
                manager.getRole(),
                manager.getPhone(),
                manager.getEmail(),
                manager.getPassword()
        );
    }

    @Override
    public Optional<Staff> get(int id) {
        String sql = "select * from staff where staff_id = ?";
        return Optional.ofNullable(template
                .queryForObject(sql, staffRowMapper, id));
    }

    public Optional<Staff> getByEmail(String email){
        String sql = "select * from staff where email = ?";
        return Optional.ofNullable(template.queryForObject(sql, staffRowMapper, email));
    }

    public List<Staff> getByShelterID(int shelterID){
        String sql = "select * from staff where shelter_id = ?";
        return template.query(sql, staffRowMapper, shelterID);
    }

    @Override
    public void update(Staff updatedStaff) {
        String sql = "update staff set name = ?, role = ?, " +
                "phone = ?, email = ?, password = ? where staff_id = ?";
        template.update(
                sql,
                updatedStaff.getName(),
                updatedStaff.getRole(),
                updatedStaff.getPhone(),
                updatedStaff.getEmail(),
                updatedStaff.getPassword(),
                updatedStaff.getStaffId()
        );
    }

    @Override
    public void delete(int id) {
        String sql = "delete from staff where staff_id = ?";
        template.update(sql, id);
    }
}
