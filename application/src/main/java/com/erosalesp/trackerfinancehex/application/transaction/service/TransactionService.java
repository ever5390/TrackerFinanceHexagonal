package com.erosalesp.trackerfinancehex.application.transaction.service;

import com.erosalesp.trackerfinancehex.application.account.ports.output.AccountPersistencePort;
import com.erosalesp.trackerfinancehex.application.transaction.ports.input.TransactionServicePort;
import com.erosalesp.trackerfinancehex.application.transaction.ports.output.TransactionPersistencePort;
import com.erosalesp.trackerfinancehex.domain.transaction.exception.TransactionAccountInsuficientBalanceException;
import com.erosalesp.trackerfinancehex.domain.account.exceptions.AccountNotFoundException;
import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.domain.transaction.models.contants.ConstErrorTransaction;
import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.domain.transaction.exception.TransactionException;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.BalanceFlow;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
public class TransactionService implements TransactionServicePort {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final TransactionPersistencePort transactionPersistencePort;
    private final AccountPersistencePort accountPersistencePort;


    public TransactionService(TransactionPersistencePort transactionPersistencePort, AccountPersistencePort accountPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Transaction save(Transaction transaction) {
        
        initializerParams(transaction);

        updateBalanceAccount(transaction);

        if(transaction.getOperationType().equals(OperationType.TRANSFERENCE)) {
            LOGGER.info("it's transference - we should create two records");

            transaction.setCodeTransfer(UUID.randomUUID().toString());
            transactionPersistencePort.save(transaction);

            // new record config
            Transaction newTransaction = getNewTransactionModified(transaction);
            updateBalanceAccount(newTransaction);
            return transactionPersistencePort.save(newTransaction);
        }

        return transactionPersistencePort.save(transaction);
    }

    private static Transaction getNewTransactionModified(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setCreateAt(transaction.getCreateAt());
        newTransaction.setDescription(transaction.getDescription());
        newTransaction.setAccount(transaction.getAccountDestiny());
        newTransaction.setBalanceFlow(BalanceFlow.INCREASE);
        newTransaction.setCodeTransfer(transaction.getCodeTransfer());
        newTransaction.setOperationType(transaction.getOperationType());
        return newTransaction;
    }

    private void updateBalanceAccount(Transaction transactionReq) {
        Account accountFound = getAccountIfExist(transactionReq.getAccount(), transactionReq.getOperationType());
        BigDecimal newCurrentBalance = getNewCurrentBalanceByBalanceFlowType(transactionReq.getBalanceFlow(), transactionReq.getAmount(), accountFound);
        Account accountUpdated = updateBalanceAccountIfValid(accountFound, newCurrentBalance);
        transactionReq.setAccount(accountUpdated);
    }

    private static BigDecimal getNewCurrentBalanceByBalanceFlowType(BalanceFlow balanceFlow, BigDecimal amount, Account account) {
        BigDecimal newCurrentBalance = BigDecimal.ZERO;

        if(balanceFlow.equals(BalanceFlow.INCREASE)) {
            newCurrentBalance = account.getCurrentBalance().add(amount);
        }

        if(balanceFlow.equals(BalanceFlow.DECREASE)) {
            newCurrentBalance = account.getCurrentBalance().subtract(amount);
        }

        return newCurrentBalance;
    }

    private Account getAccountIfExist(Account account, OperationType operationType) {

        String suffix;

        if (operationType.equals(OperationType.TRANSFERENCE)) {
            suffix = ConstErrorTransaction.TX_SUFFIX_ACCOUNT_DESTINY;
        } else {
            suffix = ConstErrorTransaction.TX_SUFFIX_ACCOUNT_ORIGIN;
        }

        if (account == null) {
            throw new AccountNotFoundException(String.format(ConstErrorTransaction.TX_ACCOUNT_NOT_SELECTED, suffix));
        }

        return accountPersistencePort.findById(account.getId())
                .orElseThrow(() -> new AccountNotFoundException(String.format(ConstErrorTransaction.TX_ACCOUNT_NOT_FOUND, suffix, account.getId())));
    }

    private Account updateBalanceAccountIfValid(Account account, BigDecimal newCurrentBalance) {
        if(newCurrentBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionAccountInsuficientBalanceException(ConstErrorTransaction.TX_ACCOUNT_INSUFICIENT_BALANCE);
        }

        account.setCurrentBalance(newCurrentBalance);
        return accountPersistencePort.save(account);
    }

    private void initializerParams(Transaction transaction) {
        
        if(transaction.getOperationType().equals(OperationType.INPUT)) {
            transaction.setBalanceFlow(BalanceFlow.INCREASE);
            transaction.setAccountDestiny(null);
        }

        if(transaction.getOperationType().equals(OperationType.OUTPUT)) {
            transaction.setBalanceFlow(BalanceFlow.DECREASE);
            transaction.setAccountDestiny(null);
        }

        if(transaction.getOperationType().equals(OperationType.TRANSFERENCE)) {
            transaction.setBalanceFlow(BalanceFlow.DECREASE);
        }

    }

    @Override
    public Transaction findById(String idTransaction) {
        return transactionPersistencePort.findById(idTransaction)
                .orElseThrow(() ->
                        new TransactionException(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,idTransaction)));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionPersistencePort.findAll();
    }

    @Override
    public Transaction update(String idTransaction, Transaction transaction) {
        return transactionPersistencePort.findById(idTransaction)
                .map(tx -> {
                    tx.setAmount(transaction.getAmount());
                    tx.setDescription(transaction.getDescription());
                    tx.setCodeTransfer(transaction.getCodeTransfer());
                    tx.setAccount(transaction.getAccount());
                    tx.setBalanceFlow(transaction.getBalanceFlow());
                    tx.setOperationType(transaction.getOperationType());
                    return transactionPersistencePort.save(tx);
                })
                .orElseThrow(()->
                        new TransactionException(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,idTransaction)));
    }

    @Override
    public void deleteById(String id) {

        if(transactionPersistencePort.findById(id).isEmpty()) {
            throw new TransactionException(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,id));
        }

        transactionPersistencePort.deleteById(id);
    }
}
