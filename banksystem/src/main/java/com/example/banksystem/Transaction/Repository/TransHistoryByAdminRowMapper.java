package com.example.banksystem.Transaction.Repository;

import com.example.banksystem.Transaction.DTO.TransHistoryByAdminDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransHistoryByAdminRowMapper implements RowMapper<TransHistoryByAdminDTO> {

    //Get all transaction history by admin
    @Override
    public TransHistoryByAdminDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransHistoryByAdminDTO transHistoryByAdminDTO = new TransHistoryByAdminDTO();
        transHistoryByAdminDTO.setRole(rs.getString("role"));
        transHistoryByAdminDTO.setTxn_date(rs.getDate("transaction_history"));
        return transHistoryByAdminDTO;
    }
}
