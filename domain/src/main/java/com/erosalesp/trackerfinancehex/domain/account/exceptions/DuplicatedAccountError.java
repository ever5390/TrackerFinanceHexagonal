package com.erosalesp.trackerfinancehex.domain.account.exceptions;

public class DuplicatedAccountError extends RuntimeException {

    public DuplicatedAccountError(String message) {
        super(message);
    }
}
