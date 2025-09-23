package com.example.banksystem.Transaction.DTO;

import java.util.Date;

public class TransHistoryByAdminDTO {

    //Get all transaction history by admin
    private Date txn_date;
    private String role;

    public TransHistoryByAdminDTO() {
    }

    public TransHistoryByAdminDTO(Date transaction_date, String role) {
        this.txn_date = transaction_date;
        this.role = role;
    }

    public Date getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(Date txn_date) {
        this.txn_date = txn_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
