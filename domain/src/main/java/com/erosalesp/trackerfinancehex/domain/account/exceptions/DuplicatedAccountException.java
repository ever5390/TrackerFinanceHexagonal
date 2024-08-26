package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class DuplicatedAccountException extends RuntimeException {

    public DuplicatedAccountException(String message) {
        super(message);
    }
}
