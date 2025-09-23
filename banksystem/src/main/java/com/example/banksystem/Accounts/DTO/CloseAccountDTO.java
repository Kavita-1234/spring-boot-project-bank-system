package com.example.banksystem.Accounts.DTO;

import com.example.banksystem.Accounts.Const.AccountsEnum;
import com.example.banksystem.Accounts.Const.StatusEnum;

import java.util.Date;

public class CloseAccountDTO {
    private int accountId;

    public CloseAccountDTO() {
    }

    public CloseAccountDTO(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
