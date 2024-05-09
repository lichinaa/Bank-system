package com.example.bank.service;

import com.example.bank.model.Account;

public interface AccountService {
    Account create(String username, double balance);
    double checkAccountBalance(Long accountId);
}
