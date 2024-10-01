package com.example.Payment_Gateway.CustomException;

public class TransactionUserException extends Exception {
    public TransactionUserException(String message) {
        super(message);
    }
}
