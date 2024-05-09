package com.example.bank.service.impl;

import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.BankRepository;
import com.example.bank.service.BankService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    public BankServiceImpl(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Bank createBank(String name, List<Long> accountIds,List<Transaction> transactions, double totalTransactionFeeAmount, double totalTransferAmount, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        List<Account> accounts = this.accountRepository.findAllById(accountIds);
        Bank bank = new Bank(name, accounts,transactions, totalTransactionFeeAmount, totalTransferAmount, transactionFlatFeeAmount, transactionPercentFeeValue);
        return this.bankRepository.save(bank);
    }

    @Override
    public List<Account> getBankAccounts(Long bankId) {
        Bank bank = this.bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));
        return bank.getAccounts();
    }

    @Override
    public double getTotalTransactionFeeAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        List<Transaction> transactions = bank.getTransactions();
        double totalTransactionFeeAmount = 0.0;

        for (Transaction transaction : transactions) {
            double transactionFee = calculateTransactionFee(bank, transaction.getAmount());
            totalTransactionFeeAmount += transactionFee;
        }

        return totalTransactionFeeAmount;
    }

    private double calculateTransactionFee(Bank bank, double transactionAmount) {
        double flatFee = bank.getTransactionFlatFeeAmount();
        double percentFeeValue = bank.getTransactionPercentFeeValue();

        double totalFee = flatFee + (percentFeeValue * transactionAmount);
        return totalFee;
    }

    @Override
    public double getTotalTransferAmount(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new IllegalArgumentException("Bank not found"));

        List<Transaction> transactions = bank.getTransactions();
        double totalTransferAmount = 0.0;

        for (Transaction transaction : transactions) {
            double transactionAmount = transaction.getAmount();
            totalTransferAmount += transactionAmount;
        }

        return totalTransferAmount;
    }

//    @Override
//    public Bank create(String name, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
//        Bank bank = new Bank();
//        bank.setName(name);
//        bank.setTransactionFlatFeeAmount(transactionFlatFeeAmount);
//        bank.setTransactionPercentFeeValue(transactionPercentFeeValue);
//        return this.bankRepository.save(bank);
//    }

}
