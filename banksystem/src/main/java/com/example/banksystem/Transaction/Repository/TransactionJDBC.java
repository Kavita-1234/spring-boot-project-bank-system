package com.example.banksystem.Transaction.Repository;

import com.example.banksystem.Transaction.DTO.TransHistoryByAdminDTO;
import com.example.banksystem.Transaction.DTO.TransHistoryByIdDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionJDBC {

    private final JdbcTemplate jdbcTemplate;

    public TransactionJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Get transaction history by account id
    public List<TransHistoryByIdDTO> findByTransHistoryByAccountId(int account_id){
        String sql = "SELECT txn_date, amount, type FROM transactions WHERE account_id = ?";
        return jdbcTemplate.query(sql, new Object[]{account_id}, new TransHistoryRowMapper());

    }

    //Get all transaction history by admin
    public List<TransHistoryByAdminDTO> findByTransHistoryByAdmin(String role){
        String sql = "select t.txn_date as transaction_history, u.role from transactions t join user u on t.user_id = u.user_id where u.role = ?";
        return jdbcTemplate.query(sql, new Object[]{role}, new TransHistoryByAdminRowMapper());

    }



}
