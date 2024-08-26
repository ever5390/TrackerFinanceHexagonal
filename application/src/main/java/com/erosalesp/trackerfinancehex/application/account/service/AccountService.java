package com.erosalesp.trackerfinancehex.application.account.service;

import com.erosalesp.trackerfinancehex.application.account.ports.input.AccountServicePort;
import com.erosalesp.trackerfinancehex.application.account.ports.output.AccountPersistencePort;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.AccountInitialBalanceException;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.AccountNotFoundException;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.DuplicatedAccountException;
import com.erosalesp.trackerfinancehex.domain.account.models.constants.ConstErrorAccount;
import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService implements AccountServicePort {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AccountPersistencePort accountPersistencePort;

    @Override
    public Account save(Account account) {
        validateDuplicatedAccountByName(account.getAccountName());
        account.setInitialBalance(account.getCurrentBalance());
        return accountPersistencePort.save(account);
    }

    private void validateDuplicatedAccountByName(String accountName) {
        Account accountDuplicated = accountPersistencePort.findByAccountName(accountName);
        if(accountDuplicated != null) {
            throw new DuplicatedAccountException(String.format(ConstErrorAccount.ACCOUNT_DUPLICATED_FOUND, accountName));
        }
    }

    @Override
    public Account findById(String idAccount) {
        return accountPersistencePort.findById(idAccount)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format(ConstErrorAccount.ACCOUNT_NOT_FOUND, idAccount)));
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountPersistencePort.findByAccountName(accountName);
    }

    @Override
    public List<Account> findAll() {
        return accountPersistencePort.findAll();
    }

    @Override
    public Account update(String idAccount, Account account) {

        Account accountFound = accountPersistencePort.findById(idAccount).orElseThrow(()->
                new AccountNotFoundException(String.format(ConstErrorAccount.ACCOUNT_NOT_FOUND, idAccount)));

        BigDecimal newInitialBalance = getInitialBalanceUpdated(account, accountFound);

        Account accountToUpdate = new Account();
        accountToUpdate.setAccountName(account.getAccountName());
        accountToUpdate.setCurrentBalance(account.getCurrentBalance());
        accountToUpdate.setInitialBalance(newInitialBalance);

        return accountPersistencePort.save(accountToUpdate);
    }

    private static BigDecimal getInitialBalanceUpdated(Account account, Account accountFound) {
        BigDecimal amountDifference = account.getCurrentBalance().subtract(accountFound.getCurrentBalance());
        BigDecimal amountInitialExist = accountFound.getInitialBalance();
        BigDecimal newInitialBalance = amountInitialExist.add(amountDifference);

        if(newInitialBalance.compareTo(BigDecimal.ZERO)<= 0) {
            throw new AccountInitialBalanceException(ConstErrorAccount.ACOUNT_INITIAL_BALANCE_ERROR_FOUND);
        }

        return newInitialBalance;
    }

    @Override
    public void deleteById(String id) {

        if(accountPersistencePort.findById(id).isEmpty()) {
            throw new AccountNotFoundException(String.format(ConstErrorAccount.ACCOUNT_NOT_FOUND, id));
        }

        accountPersistencePort.deleteById(id);
    }
}
