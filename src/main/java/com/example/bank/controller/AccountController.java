package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.model.exceptions.NegativeBalanceException;
import com.example.bank.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Account createAccount(@RequestParam String username, @RequestParam double balance) throws NegativeBalanceException {
        return accountService.create(username, balance);
    }

    @GetMapping("/balance/{accountId}")
    public double checkAccountBalance(@PathVariable Long accountId) {
        return accountService.checkAccountBalance(accountId);
    }
}
