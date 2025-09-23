package com.example.banksystem.User.Repository;

import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import com.example.banksystem.Accounts.Repository.UserAccountRowMapper;
import com.example.banksystem.User.DTO.AdminTransactionDTO;
import com.example.banksystem.User.DTO.UserAdminDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAdminJDBC {
    private final JdbcTemplate jdbcTemplate;

    public UserAdminJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get all user admin
    public List<UserAdminDTO> findByAdminUser(String role){
        String sql = "select * from user where role = ?";
        return jdbcTemplate.query(sql, new Object[]{role} ,new UserAdminRowMapper());
    }

    //Get list of user admin
    public List<AdminTransactionDTO> findByAdminTransaction(String role){
        String sql = "select t.*, u.role as user_role from transactions t join user u on t.user_id = u.user_id where u.role = ?";
        return jdbcTemplate.query(sql, new Object[]{role}, new AdminTransactionRowMapper());
    }
}
