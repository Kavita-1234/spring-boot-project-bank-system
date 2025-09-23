package com.example.banksystem.Transaction.Repository;

import com.example.banksystem.Transaction.Const.TransactionEnum;
import com.example.banksystem.Transaction.DTO.TransHistoryByIdDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransHistoryRowMapper implements RowMapper<TransHistoryByIdDTO> {

    @Override
    public TransHistoryByIdDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransHistoryByIdDTO dto = new TransHistoryByIdDTO();
        dto.setTxnDate(rs.getTimestamp("txn_date"));        // map the correct DB column
        dto.setAmount(rs.getInt("amount"));                 // map amount
        dto.setType(TransactionEnum.valueOf(rs.getString("type"))); // map type enum
        return dto;
    }
}
