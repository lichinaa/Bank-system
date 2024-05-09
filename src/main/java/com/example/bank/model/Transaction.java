package com.example.bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    private double amount;
    private long originatingAccountId;
    private long resultingAccountId;
    private String transactionReason;

    @ManyToOne
    private Bank bank;

    public Transaction() {
    }

    public Transaction(double amount, long originatingAccountId, long resultingAccountId, String transactionReason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getOriginatingAccountId() {
        return originatingAccountId;
    }

    public void setOriginatingAccountId(long originatingAccountId) {
        this.originatingAccountId = originatingAccountId;
    }

    public long getResultingAccountId() {
        return resultingAccountId;
    }

    public void setResultingAccountId(long resultingAccountId) {
        this.resultingAccountId = resultingAccountId;
    }

    public String getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }

    public Bank getBank() {return bank;}

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
