package com.example.banksystem.Accounts.Entity;

import com.example.banksystem.Accounts.Const.AccountsEnum;
import com.example.banksystem.Accounts.Const.StatusEnum;
import com.example.banksystem.User.Entity.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AccountsEnum account_type;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "account_number")
    private int accountNumber;
    private int balance;

    @Column(name="opened_date")
    private Date openedDate;

    public Accounts() {
    }

    public Accounts(int accountId, User user, AccountsEnum account_type, StatusEnum status, int accountNumber, int balance, Date openedDate) {
        this.accountId = accountId;
        this.user = user;
        this.account_type = account_type;
        this.status = status;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openedDate = openedDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountsEnum getAccount_type() {
        return account_type;
    }

    public void setAccount_type(AccountsEnum account_type) {
        this.account_type = account_type;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Date openedDate) {
        this.openedDate = openedDate;
    }
}
