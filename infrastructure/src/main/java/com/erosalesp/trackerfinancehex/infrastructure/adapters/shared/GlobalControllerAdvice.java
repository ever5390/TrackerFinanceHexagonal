package com.erosalesp.trackerfinancehex.infrastructure.adapters.shared;

import com.erosalesp.trackerfinancehex.domain.account.exceptions.AccountInitialBalanceException;
import com.erosalesp.trackerfinancehex.domain.transaction.exception.TransactionAccountInsuficientBalanceException;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.AccountNotFoundException;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.DuplicatedAccountException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(AccountNotFoundException.class)
    public ErrorResponse handleAccountNotFound(AccountNotFoundException error) {
        LOGGER.error(error.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(TransactionAccountInsuficientBalanceException.class)
    public ErrorResponse handleAccountInsuficientBalance(TransactionAccountInsuficientBalanceException error) {
        LOGGER.error(error.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(DuplicatedAccountException.class)
    public ErrorResponse duplicatedAccountError(DuplicatedAccountException error) {
        LOGGER.error(error.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(AccountInitialBalanceException.class)
    public ErrorResponse handleAccountInitialBalanceErrorFound(AccountInitialBalanceException error) {
        LOGGER.error(error.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(error.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        result.getFieldErrors()
                .forEach(error -> {
                    LOGGER.error(error.getDefaultMessage());
                });

        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGenericError(Exception exception) {
        LOGGER.error(exception.getMessage());
        return ErrorResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message("Ocurrió un error al procesar la operación, por favor reinicie la aplicación.")
                .timestamp(LocalDateTime.now())
                .build();
    }


}
