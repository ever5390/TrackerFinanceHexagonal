package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.mapper;

import com.erosalesp.trackerfinancehex.domain.transaction.models.entity.Transaction;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.request.TransactionCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.response.TransactionResponse;

import java.util.List;

public interface TransactionRestMapper {

    Transaction toTransactionDomain(TransactionCreateRequest TransactionCreateRequest);

    TransactionResponse toTransactionResponse(Transaction transactionDomain);

    List<TransactionResponse> toTransactionResponseList(List<Transaction> transactionDomainList);
}
