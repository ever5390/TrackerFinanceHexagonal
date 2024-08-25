package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence;

import com.erosalesp.trackerfinancehex.application.transaction.ports.output.TransactionPersistencePort;
import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.entity.TransactionEntity;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.mapper.TransactionPersistenceMapper;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.repository.TransactionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

    private final TransactionRepository transactionRepository;
    private final TransactionPersistenceMapper transactionPersistenceMapper;

    public TransactionPersistenceAdapter(TransactionRepository transactionRepository, TransactionPersistenceMapper transactionPersistenceMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionPersistenceMapper = transactionPersistenceMapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = transactionPersistenceMapper.toTransactionEntity(transaction);
        return transactionPersistenceMapper.toTransactionDomain(transactionRepository.save(transactionEntity));
    }

    @Override
    public Optional<Transaction> findById(String idTransaction) {
        return transactionRepository.findById(idTransaction).
                map(transactionPersistenceMapper::toTransactionDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionPersistenceMapper.toTransactionDomainList(transactionRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        transactionRepository.deleteById(id);
    }
}
