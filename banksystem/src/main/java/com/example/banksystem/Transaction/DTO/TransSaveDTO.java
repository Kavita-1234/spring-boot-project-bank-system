package com.example.banksystem.Transaction.DTO;

import com.example.banksystem.Transaction.Const.TransactionEnum;

import java.util.Date;

public class TransSaveDTO {

    private int accountId;
    private int amount;
    private TransactionEnum type;
    private Date txnDate;
    private Integer receiverAccountId;

    public TransSaveDTO() {
    }

    public TransSaveDTO(int accountId, int amount, TransactionEnum type, Date txnDate, Integer receiverAccountId) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.txnDate = txnDate;
        this.receiverAccountId = receiverAccountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public Integer getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Integer receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }
}
