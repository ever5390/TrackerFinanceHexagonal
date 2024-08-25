package com.erosalesp.trackerfinancehex.application.transaction.ports.input;

import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;

import java.util.List;

public interface TransactionServicePort {

    Transaction save(Transaction transaction);

    Transaction findById(String idTransaction);

    List<Transaction> findAll();

    Transaction update(String idTransaction, Transaction transaction);

    void deleteById(String id);
}
