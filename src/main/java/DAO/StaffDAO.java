package DAO;

import Model.Staff;
import Model.StaffBuilder;
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
        this.staffRowMapper = ((rs, rowNum) -> {
            return new StaffBuilder()
                    .setID(rs.getInt("staffID"))
                    .setName(rs.getString("name"))
                    .setRole(rs.getString("role"))
                    .setPhone(rs.getString("phone"))
                    .setEmail(rs.getString("email"))
                    .setPassword(rs.getString("password"))
                    .setShelterID(rs.getInt("shelterID"))
                    .get();
        });
    }

    @Override
    public void add(Staff staff) {
        //for adding normal staff
        String sql = "insert into staff (name, role, phone, " +
                "email, password, shelterID) values (?,?,?,?,?,?)";
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
        String sql = "insert into staff (name, role, phone, " +
                "email, password) values (?,?,?,?,?)";
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
        String sql = "select * from staff where staffID = ?";
        List<Staff> staffList = template.query(sql, staffRowMapper, id);
        return Optional.ofNullable(
                staffList.isEmpty() ? null : staffList.get(0)
        );
    }

    public Optional<Staff> getByEmail(String email){
        String sql = "select * from staff where email = ?";
        List<Staff> staffList = template.query(sql, staffRowMapper, email);
        return Optional.ofNullable(
                staffList.isEmpty() ? null : staffList.get(0)
        );
    }

    public List<Staff> getByShelterID(int shelterID){
        String sql = "select * from staff where shelterID = ?";
        return template.query(sql, staffRowMapper, shelterID);
    }

    @Override
    public void update(Staff updatedStaff) {
        String sql = "update staff set name = ?, role = ?, " +
                "phone = ?, email = ?, password = ? where staffID = ?";
        template.update(
                sql,
                updatedStaff.getName(),
                updatedStaff.getRole(),
                updatedStaff.getPhone(),
                updatedStaff.getEmail(),
                updatedStaff.getPassword(),
                updatedStaff.getId()
        );
    }

    @Override
    public void delete(int id) {
        String sql = "delete from staff where staffID = ?";
        template.update(sql, id);
    }
}
