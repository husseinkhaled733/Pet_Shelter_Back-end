package com.example.demo.DAO;

import com.example.demo.Model.Staff;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SecDAO {
    private JdbcTemplate template;

    public SecDAO(JdbcTemplate jdbcTemplate) {
        this.template = jdbcTemplate;
    }

    public void addManager(Staff staff) {
        String usersSql = "insert into users (username, password, enabled) values (?,?,?)";
        String authoritiesSql = "insert into authorities (username, authority) values (?,?)";
        template.update(
                usersSql,
                staff.getEmail(),
                "{bcrypt}" + staff.getPassword(),
                true
        );
        template.update(
                authoritiesSql,
                staff.getEmail(),
                "ROLE_MANAGER"
        );
    }


    public void addStaff(Staff staff) {
        String usersSql = "insert into users (username, password, enabled) values (?,?,?)";
        String authoritiesSql = "insert into authorities (username, authority) values (?,?)";
        template.update(
                usersSql,
                staff.getEmail(),
                "{bcrypt}" + staff.getPassword(),
                true
        );
        template.update(
                authoritiesSql,
                staff.getEmail(),
                "ROLE_STAFF"
        );
    }

    public void addAdopter(Staff staff) {
        String usersSql = "insert into users (username, password, enabled) values (?,?,?)";
        String authoritiesSql = "insert into authorities (username, authority) values (?,?)";
        template.update(
                usersSql,
                staff.getEmail(),
                "{bcrypt}" + staff.getPassword(),
                true
        );
        template.update(
                authoritiesSql,
                staff.getEmail(),
                "ROLE_ADOPTER"
        );
    }
}
