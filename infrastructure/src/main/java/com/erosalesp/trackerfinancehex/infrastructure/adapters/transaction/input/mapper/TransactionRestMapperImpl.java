package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.mapper;


import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.request.TransactionCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionRestMapperImpl implements TransactionRestMapper {
    @Override
    public Transaction toTransactionDomain(TransactionCreateRequest transactionCreateRequest) {

        Transaction transactionDomain = new Transaction();
        transactionDomain.setAmount(transactionCreateRequest.getAmount());
        transactionDomain.setCreateAt(transactionCreateRequest.getCreateAt());
        transactionDomain.setDescription(transactionCreateRequest.getDescription());
        transactionDomain.setAccount(transactionCreateRequest.getAccount());
        transactionDomain.setAccountDestiny(transactionCreateRequest.getAccountDestiny());
        transactionDomain.setBalanceFlow(transactionCreateRequest.getBalanceFlow());
        transactionDomain.setOperationType(transactionCreateRequest.getOperationType());

        return transactionDomain;
    }

    @Override
    public TransactionResponse toTransactionResponse(Transaction transactionDomain) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(transactionDomain.getId());
        transactionResponse.setAmount(transactionDomain.getAmount());
        transactionResponse.setCreateAt(transactionDomain.getCreateAt());
        transactionResponse.setDescription(transactionDomain.getDescription());
        transactionResponse.setAccount(transactionDomain.getAccount());
        transactionResponse.setBalanceFlow(transactionDomain.getBalanceFlow());
        transactionResponse.setOperationType(transactionDomain.getOperationType());

        return transactionResponse;
    }

    @Override
    public List<TransactionResponse> toTransactionResponseList(List<Transaction> transactionDomainList) {
        return transactionDomainList.stream()
                .map(txD -> {
                    TransactionResponse transactionResponse = new TransactionResponse();
                    transactionResponse.setId(txD.getId());
                    transactionResponse.setAmount(txD.getAmount());
                    transactionResponse.setCreateAt(txD.getCreateAt());
                    transactionResponse.setDescription(txD.getDescription());
                    transactionResponse.setAccount(txD.getAccount());
                    transactionResponse.setBalanceFlow(txD.getBalanceFlow());
                    transactionResponse.setOperationType(txD.getOperationType());
                    return transactionResponse;
                })
                .collect(Collectors.toList());
    }
}
