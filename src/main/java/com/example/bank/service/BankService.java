package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;

import java.util.List;

public interface BankService {
    Bank createBank(String name, List<Long> accounts, List<Transaction> transactions, double totalTransactionFeeAmount, double totalTransferAmount, double transactionFlatFeeAmount, double transactionPercentFeeValue);
    List<Account> getBankAccounts(Long bankId);
    double getTotalTransactionFeeAmount(Long bankId);
    double getTotalTransferAmount(Long bankId);
//    Bank create(String name, double transactionFlatFeeAmount, double transactionPercentFeeValue);
}
