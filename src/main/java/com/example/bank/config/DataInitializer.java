package com.example.bank.config;

import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Transaction;
import com.example.bank.repository.BankRepository;
import com.example.bank.service.AccountService;
import com.example.bank.service.BankService;
import com.example.bank.service.TransactionService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final BankService bankService;

    public DataInitializer(AccountService accountService, TransactionService transactionService, BankService bankService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.bankService = bankService;
    }

    @PostConstruct
    public void initData() {
        List<Account> accounts = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            if(i<6) {
                Account account = this.accountService.create("User" + i, 5000.0);
                accounts.add(account);
            }else{
                Account account = this.accountService.create("User" + i, 7000.0);
                accounts.add(account);
            }
        }


        for (int i = 1; i < accounts.size() - 1; i++) {
            Account sender = accounts.get(i);
            Account receiver = accounts.get(i + 1);
            Transaction transaction = this.transactionService.create(100.0, sender.getAccountId(), receiver.getAccountId(), "Transfer");
            transactions.add(transaction);
        }

        double totalTransactionFeeAmount = 100.0;
        double totalTransferAmount = 25000.0;
        double transactionFlatFeeAmount = 10.0;
        double transactionPercentFeeValue = 0.05;
        List<Long> bankAccounts1 = new ArrayList<>();
        List<Long> bankAccounts2 = new ArrayList<>();
        List<Transaction> bankTransaction1 = new ArrayList<>();
        List<Transaction> bankTransaction2 = new ArrayList<>();

        for(int i=0; i<accounts.size(); i++){
            if(i<5){
                bankAccounts1.add(accounts.get(i).getAccountId());
            }else{
                bankAccounts2.add(accounts.get(i).getAccountId());
            }
        }

        for(int i=0; i<transactions.size(); i++){
            if(i<4){
                bankTransaction1.add(transactions.get(i));
            }else {
                bankTransaction2.add(transactions.get(i));
            }
        }

        Bank bank1 = bankService.createBank("Bank1", bankAccounts1, bankTransaction1, totalTransactionFeeAmount, totalTransferAmount, transactionFlatFeeAmount, transactionPercentFeeValue);
        Bank bank2 = bankService.createBank("Bank2", bankAccounts2, bankTransaction2, totalTransactionFeeAmount, totalTransferAmount, transactionFlatFeeAmount, transactionPercentFeeValue);

    }
}
