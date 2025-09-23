package com.example.banksystem.Accounts.Repository;

import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserAccountJDBC {

    private final JdbcTemplate jdbcTemplate;

    public UserAccountJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get all user account details
    public List<UserAccountDTO> findByUserAccount(){
        String sql = "select a.* from accounts a join user u on u.user_id = a.user_id";
        return jdbcTemplate.query(sql, new UserAccountRowMapper());
    }

    //Get account details by id
    public List<UserAccountDTO> findByAccountId(int account_id){
        String sql = "select * from accounts where account_id = ?";
        return jdbcTemplate.query(sql, new Object[]{account_id}, new UserAccountRowMapper());
    }

}
