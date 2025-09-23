package com.example.banksystem.Transaction.DTO;

public class TransWithdrawAmountDTO {

    private int accountId;
    private int amount;

    public TransWithdrawAmountDTO() {
    }

    public TransWithdrawAmountDTO(int accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
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
}