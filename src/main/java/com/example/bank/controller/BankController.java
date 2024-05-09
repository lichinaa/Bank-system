package com.example.bank.controller;

import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;
import com.example.bank.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/createWithAccounts")
    public String createBankWithAccounts(@RequestParam String name, @RequestParam List<Long> accountIds, @RequestParam List<Transaction> transactions,
                                         @RequestParam double totalTransactionFeeAmount, @RequestParam double totalTransferAmount,
                                         @RequestParam double transactionFlatFeeAmount, @RequestParam double transactionPercentFeeValue) {
        Bank bank = bankService.createBank(name, accountIds,transactions, totalTransactionFeeAmount, totalTransferAmount, transactionFlatFeeAmount, transactionPercentFeeValue);
        return "Bank created with ID: " + bank.getBankid();
    }

    @GetMapping("/accounts/{bankId}")
    public List<Account> getBankAccounts(@PathVariable Long bankId) {
        return bankService.getBankAccounts(bankId);
    }

    @GetMapping("/totalTransactionFeeAmount/{bankId}")
    public double getTotalTransactionFeeAmount(@PathVariable Long bankId) {
        return bankService.getTotalTransactionFeeAmount(bankId);
    }

    @GetMapping("/totalTransferAmount/{bankId}")
    public double getTotalTransferAmount(@PathVariable Long bankId) {
        return bankService.getTotalTransferAmount(bankId);
    }

//    @PostMapping("/create")
//    public String createBank(@RequestParam String name, @RequestParam double transactionFlatFeeAmount, @RequestParam double transactionPercentFeeValue) {
//        Bank bank = bankService.create(name, transactionFlatFeeAmount, transactionPercentFeeValue);
//        return "Bank created with ID: " + bank.getBankid();
//    }
}
