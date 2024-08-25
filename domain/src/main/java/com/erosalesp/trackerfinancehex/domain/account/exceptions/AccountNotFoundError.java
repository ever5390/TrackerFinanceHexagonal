package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class AccountNotFoundError extends RuntimeException {

    public AccountNotFoundError(String message) {
        super(message);
    }
}
