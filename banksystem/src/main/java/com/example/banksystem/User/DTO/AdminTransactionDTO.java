package com.example.banksystem.User.DTO;

import java.util.Date;

public class AdminTransactionDTO {
    private int txnId;
    private int accountId;
    private int userId;
    private String amount;
    private String type;
    private String role;
    private Date transactionDate;
    private int relatedAccountId;

    public AdminTransactionDTO() {
    }

    public AdminTransactionDTO(int txnId, int accountId, int userId, String amount, String type, String role, Date transactionDate, int relatedAccountId) {
        this.txnId = txnId;
        this.accountId = accountId;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.role = role;
        this.transactionDate = transactionDate;
        this.relatedAccountId = relatedAccountId;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(int relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }
}
