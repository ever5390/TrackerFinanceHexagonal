package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
