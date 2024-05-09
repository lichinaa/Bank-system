package com.example.bank.model;


import com.example.bank.model.exceptions.NegativeBalanceException;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private String username;
    private double balance;

    public Account() {
        this.balance = 0.0;
    }

    public Account(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }
        this.balance = balance;
    }
}
