package com.example.banksystem.Transaction.Entity;

import com.example.banksystem.Accounts.Entity.Accounts;
import com.example.banksystem.Transaction.Const.TransactionEnum;
import com.example.banksystem.User.Entity.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private int txnId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts accounts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int amount;

    @Enumerated(EnumType.STRING)
    private TransactionEnum type;

    @Column(name = "txn_date")
    private Date txnDate;

    @ManyToOne
    @JoinColumn(name = "related_account_id")
    private Accounts relatedAccountId;

    public Transactions() {
    }

    public Transactions(int txnId, Accounts accounts, User user, int amount, TransactionEnum type, Date txnDate, Accounts relatedAccountId) {
        this.txnId = txnId;
        this.accounts = accounts;
        this.user = user;
        this.amount = amount;
        this.type = type;
        this.txnDate = txnDate;
        this.relatedAccountId = relatedAccountId;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Accounts getRelatedAccountId() {
        return relatedAccountId;
    }

    public void setRelatedAccountId(Accounts relatedAccountId) {
        this.relatedAccountId = relatedAccountId;
    }
}
