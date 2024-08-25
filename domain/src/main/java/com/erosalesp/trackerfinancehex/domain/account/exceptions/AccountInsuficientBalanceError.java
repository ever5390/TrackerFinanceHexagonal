package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class AccountInsuficientBalanceError extends RuntimeException {

    public AccountInsuficientBalanceError(String message) {
        super(message);
    }
}