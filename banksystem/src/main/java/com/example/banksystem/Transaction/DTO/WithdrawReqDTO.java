package com.example.banksystem.Transaction.DTO;

import com.example.banksystem.Transaction.Const.TransactionEnum;

import java.util.Date;

public class WithdrawReqDTO {
    private int accountId;
    private int userId;
    private int txnId;
    private int amount;
    private int relatedAccountId;
    private TransactionEnum type;
    private Date txnDate;

    public WithdrawReqDTO() {
    }

    public WithdrawReqDTO(int accountId, int userId, int txnId, int amount, int relatedAccountId, TransactionEnum type, Date txnDate) {
        this.accountId = accountId;
        this.userId = userId;
        this.txnId = txnId;
        this.amount = amount;
        this.relatedAccountId = relatedAccountId;
        this.type = type;
        this.txnDate = txnDate;
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

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(int relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }

    public TransactionEnum getType() {
        return type;
    }

    public void setType(TransactionEnum type) {
        this.type = type;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }
}
