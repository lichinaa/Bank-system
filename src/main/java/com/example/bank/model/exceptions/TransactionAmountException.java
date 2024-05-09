package com.example.bank.model.exceptions;

public class TransactionAmountException extends RuntimeException {
    public TransactionAmountException(String message) {
        super(message);
    }
}
