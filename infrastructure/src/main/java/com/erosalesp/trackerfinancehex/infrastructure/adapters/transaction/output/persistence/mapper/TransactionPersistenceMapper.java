package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.mapper;


import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.entity.TransactionEntity;

import java.util.List;

public interface TransactionPersistenceMapper {

    TransactionEntity toTransactionEntity(Transaction transactionDomain);
    Transaction toTransactionDomain(TransactionEntity transactionEntity);
    List<Transaction> toTransactionDomainList(List<TransactionEntity> transactionEntityList);
}