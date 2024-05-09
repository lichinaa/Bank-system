package com.example.bank.service.impl;

import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.model.exceptions.AccountNotFoundException;
import com.example.bank.model.exceptions.TransactionAmountException;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public void withdrawal(double amount, long originatingAccountId) {
        Account account= accountRepository.findById(originatingAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Originating account not found"));

        if (amount <= 0) {
            throw new TransactionAmountException("Withdrawal amount must be greater than zero");
        }
        if (account.getBalance() < amount) {
            throw new TransactionAmountException("Don't have enough money.");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public void deposit(double amount, long resultingAccountId) {
        Account account = accountRepository.findById(resultingAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Resulting account not found"));

        if (amount <= 0) {
            throw new TransactionAmountException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void performTransfer(double amount, long originatingAccountId, long resultingAccountId) {
        Account originatingAccount = accountRepository.findById(originatingAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Originating account not found"));
        Account resultingAccount = accountRepository.findById(resultingAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Resulting account not found"));

        if (amount <= 0) {
            throw new TransactionAmountException("Transfer amount must be greater than zero");
        }
        if (originatingAccount.getBalance() < amount) {
            throw new TransactionAmountException("Don't have enough money.");
        }

        originatingAccount.setBalance(originatingAccount.getBalance() - amount);
        resultingAccount.setBalance(resultingAccount.getBalance() + amount);

        accountRepository.save(originatingAccount);
        accountRepository.save(resultingAccount);
    }

    @Override
    public List<Transaction> getAccountTransactions(Long accountId) {
        List<Transaction> allTransaction = transactionRepository.findAll();
        List<Transaction> transactions = new ArrayList<>();

        for (Transaction transaction : allTransaction) {
            if (transaction.getOriginatingAccountId() == accountId ||
                    transaction.getResultingAccountId() == accountId) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    @Override
    public Transaction create(double amount, long originatingAccountId, long resultingAccountId, String transactionReason) {
        Transaction transaction = new Transaction(amount, originatingAccountId, resultingAccountId, transactionReason);
        return this.transactionRepository.save(transaction);
    }

}
