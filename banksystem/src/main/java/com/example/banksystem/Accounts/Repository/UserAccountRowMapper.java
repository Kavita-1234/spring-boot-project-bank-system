package com.example.banksystem.Accounts.Repository;

import com.example.banksystem.Accounts.DTO.UserAccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRowMapper implements RowMapper<UserAccountDTO> {

    @Override
    public UserAccountDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccountDTO accountDTO = new UserAccountDTO();
        accountDTO.setAccountId(rs.getInt("account_id"));
        accountDTO.setUserId(rs.getInt("user_id"));
        accountDTO.setAccountNumber(rs.getInt("account_number"));
        accountDTO.setAccountType(rs.getString("account_type"));
        accountDTO.setStatus(rs.getString("status"));
        accountDTO.setBalance(rs.getInt("balance"));
        accountDTO.setOpenedDate(rs.getDate("opened_date"));
        return accountDTO;
    }
}
