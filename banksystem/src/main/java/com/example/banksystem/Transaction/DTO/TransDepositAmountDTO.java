package com.example.banksystem.Transaction.DTO;

public class TransDepositAmountDTO {
    private int accountId;
    private int amount;

    public TransDepositAmountDTO() {
    }

    public TransDepositAmountDTO(int accountId, int amount) {
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
