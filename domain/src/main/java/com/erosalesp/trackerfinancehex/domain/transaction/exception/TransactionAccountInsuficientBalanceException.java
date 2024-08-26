package com.erosalesp.trackerfinancehex.domain.transaction.exception;

public class TransactionAccountInsuficientBalanceException extends RuntimeException {

    public TransactionAccountInsuficientBalanceException(String message) {
        super(message);
    }
}