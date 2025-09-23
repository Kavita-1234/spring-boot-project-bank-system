package com.example.banksystem.Transaction.DTO;

import com.example.banksystem.Transaction.Const.TransactionEnum;

import java.util.Date;

public class TransAmountReqDTO {
    private int senderAccountId;
    private int receiverAccountId;
    private int amount;
    private TransactionEnum type;
    private Date txn_date;

    public TransAmountReqDTO() {
    }

    public TransAmountReqDTO(int senderAccountId, int receiverAccountId, int amount, TransactionEnum type, Date txn_date) {
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.amount = amount;
        this.type = type;
        this.txn_date = txn_date;
    }

    public int getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(int receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
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

    public Date getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(Date txn_date) {
        this.txn_date = txn_date;
    }
}
