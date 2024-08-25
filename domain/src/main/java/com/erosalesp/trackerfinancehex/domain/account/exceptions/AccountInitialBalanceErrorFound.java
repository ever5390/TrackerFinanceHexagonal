package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class AccountInitialBalanceErrorFound extends  RuntimeException {
    public AccountInitialBalanceErrorFound(String message) {
        super(message);
    }

}
