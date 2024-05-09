package com.example.bank.model.exceptions;

public class NegativeBalanceException extends RuntimeException{
    public NegativeBalanceException(String message) {
        super(message);
    }
}
