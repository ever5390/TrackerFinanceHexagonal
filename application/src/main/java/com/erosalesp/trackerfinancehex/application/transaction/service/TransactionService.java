package com.erosalesp.trackerfinancehex.application.transaction.service;

import com.erosalesp.trackerfinancehex.application.transaction.ports.input.TransactionServicePort;
import com.erosalesp.trackerfinancehex.application.transaction.ports.output.TransactionPersistencePort;
import com.erosalesp.trackerfinancehex.domain.transaction.models.contants.ConstErrorTransaction;
import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.domain.transaction.exception.ExceptionTransactionNotFound;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService implements TransactionServicePort {

    private final TransactionPersistencePort transactionPersistencePort;

    public TransactionService(TransactionPersistencePort transactionPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionPersistencePort.save(transaction);
    }

    @Override
    public Transaction findById(String idTransaction) {
        return transactionPersistencePort.findById(idTransaction)
                .orElseThrow(() ->
                        new ExceptionTransactionNotFound(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,idTransaction)));
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
                        new ExceptionTransactionNotFound(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,idTransaction)));
    }

    @Override
    public void deleteById(String id) {

        if(transactionPersistencePort.findById(id).isEmpty()) {
            throw new ExceptionTransactionNotFound(String.format(ConstErrorTransaction.TRANSACTION_NOT_FOUND,id));
        }

        transactionPersistencePort.deleteById(id);
    }
}
