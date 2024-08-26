package com.erosalesp.trackerfinancehex.domain.transaction.exception;

public class TransactionAccountNotFoundException extends RuntimeException {

    public TransactionAccountNotFoundException(String message) {
        super(message);
    }
}
