package com.example.bank.controller;

import com.example.bank.model.Transaction;
import com.example.bank.model.exceptions.AccountNotFoundException;
import com.example.bank.model.exceptions.TransactionAmountException;
import com.example.bank.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountId}")
    public List<Transaction> getAccountTransactions(@PathVariable Long accountId) {
        return transactionService.getAccountTransactions(accountId);
    }

    @PostMapping("/withdrawal")
    public String withdrawal(@RequestParam double amount, @RequestParam long originatingAccountId) {
        try {
            transactionService.withdrawal(amount, originatingAccountId);
            return "Withdrawal successful";
        } catch (TransactionAmountException | AccountNotFoundException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amount, @RequestParam long resultingAccountId){
        try {
            transactionService.deposit(amount, resultingAccountId);
            return "Deposit successful";
        } catch (TransactionAmountException | AccountNotFoundException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam double amount, @RequestParam long originatingAccountId, @RequestParam long resultingAccountId) {
        try {
            transactionService.performTransfer(amount, originatingAccountId, resultingAccountId);
            return "Transfer successful";
        } catch (TransactionAmountException | AccountNotFoundException e) {
            return e.getMessage();
        }
    }

}
