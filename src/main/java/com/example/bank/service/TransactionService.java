package com.example.bank.service;

import com.example.bank.model.Transaction;

import java.util.List;

public interface TransactionService {
    void withdrawal(double amount, long originatingAccountId);
    void deposit(double amount, long resultingAccountId);
    void performTransfer(double amount,long originatingAccountId, long resultingAccountId);
    List<Transaction> getAccountTransactions(Long accountId);

    Transaction create(double amount, long originatingAccountId, long resultingAccountId, String transactionReason);
}
