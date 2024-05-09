package com.example.bank.service.impl;

import com.example.bank.model.Account;
import com.example.bank.model.exceptions.AccountNotFoundException;
import com.example.bank.model.exceptions.NegativeBalanceException;
import com.example.bank.repository.AccountRepository;
import com.example.bank.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(String username, double balance){
        if (balance<0){
            throw new NegativeBalanceException("Initial balance cannot be negative");
        }
        Account account = new Account(username, balance);
        return this.accountRepository.save(account);
    }
    @Override
    public double checkAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getBalance();
    }
}
