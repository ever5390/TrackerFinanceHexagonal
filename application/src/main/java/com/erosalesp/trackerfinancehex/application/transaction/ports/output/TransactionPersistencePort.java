package com.erosalesp.trackerfinancehex.application.transaction.ports.output;

import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionPersistencePort {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(String idTransaction);

    List<Transaction> findAll();

    void deleteById(String id);
}
