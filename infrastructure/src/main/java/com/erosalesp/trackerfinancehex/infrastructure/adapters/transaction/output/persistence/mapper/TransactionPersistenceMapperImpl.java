package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.mapper;


import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.mapper.AccountPersistenceMapper;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionPersistenceMapperImpl implements TransactionPersistenceMapper{

    private final AccountPersistenceMapper accountPersistenceMapper;

    public TransactionPersistenceMapperImpl(AccountPersistenceMapper accountPersistenceMapper) {
        this.accountPersistenceMapper = accountPersistenceMapper;
    }

    @Override
    public TransactionEntity toTransactionEntity(Transaction transactionDomain) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transactionDomain.getAmount());
        transactionEntity.setCreateAt(transactionDomain.getCreateAt());
        transactionEntity.setDescription(transactionDomain.getDescription());
        transactionEntity.setAccount(accountPersistenceMapper.toAccountEntity(transactionDomain.getAccount()));
        transactionEntity.setBalanceFlow(transactionDomain.getBalanceFlow());
        transactionEntity.setOperationType(transactionDomain.getOperationType());

        return transactionEntity;
    }

    @Override
    public Transaction toTransactionDomain(TransactionEntity transactionEntity) {
        Transaction transactionDomain = new Transaction();
        transactionDomain.setId(transactionEntity.getId());
        transactionDomain.setAmount(transactionEntity.getAmount());
        transactionDomain.setCreateAt(transactionEntity.getCreateAt());
        transactionDomain.setDescription(transactionEntity.getDescription());
        transactionDomain.setAccount(accountPersistenceMapper.toAccountDomain(transactionEntity.getAccount()));
        transactionDomain.setBalanceFlow(transactionEntity.getBalanceFlow());
        transactionDomain.setOperationType(transactionEntity.getOperationType());

        return transactionDomain;
    }

    @Override
    public List<Transaction> toTransactionDomainList(List<TransactionEntity> transactionEntityList) {
        return transactionEntityList.stream()
                .map(transactionEntity -> {
                    Transaction transactionDomain = new Transaction();
                    transactionDomain.setId(transactionEntity.getId());
                    transactionDomain.setAmount(transactionEntity.getAmount());
                    transactionDomain.setCreateAt(transactionEntity.getCreateAt());
                    transactionDomain.setDescription(transactionEntity.getDescription());
                    transactionDomain.setAccount(accountPersistenceMapper.toAccountDomain(transactionEntity.getAccount()));
                    transactionDomain.setBalanceFlow(transactionEntity.getBalanceFlow());
                    transactionDomain.setOperationType(transactionEntity.getOperationType());

                    return transactionDomain;
                })
                .collect(Collectors.toList());
    }
}
