package com.example.banksystem.Transaction.DTO;

import com.example.banksystem.Transaction.Const.TransactionEnum;

import java.util.Date;

public class TransHistoryByIdDTO {
    private Date txnDate;
    private int amount;
    private TransactionEnum type;


    public TransHistoryByIdDTO() {
    }

    public TransHistoryByIdDTO(Date txnDate, int amount, TransactionEnum type) {
        this.txnDate = txnDate;
        this.amount = amount;
        this.type = type;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
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
}
