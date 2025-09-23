package com.example.banksystem.User.Repository;

import com.example.banksystem.User.DTO.AdminTransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminTransactionRowMapper implements RowMapper<AdminTransactionDTO> {

    @Override
    public AdminTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdminTransactionDTO adminTransactionDTO = new AdminTransactionDTO();
        adminTransactionDTO.setTxnId(rs.getInt("txn_id"));
        adminTransactionDTO.setUserId(rs.getInt("user_id"));
        adminTransactionDTO.setAccountId(rs.getInt("account_id"));
        adminTransactionDTO.setAmount(rs.getString("amount"));
        adminTransactionDTO.setRole(rs.getString("user_role"));
        adminTransactionDTO.setType(rs.getString("type"));
        adminTransactionDTO.setTransactionDate(rs.getDate("txn_date"));
        adminTransactionDTO.setRelatedAccountId(rs.getInt("related_account_id"));
        return adminTransactionDTO;
    }
}
