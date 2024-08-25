package com.erosalesp.trackerfinancehex.domain.transaction.models.entity;

import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.OperationType;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.BalanceFlow;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    private String id;
    private BigDecimal amount;
    private LocalDateTime createAt;
    private String description;
    private String codeTransfer;
    private Account account;
    private Account accountDestiny;
    private BalanceFlow BalanceFlow;
    private OperationType operationType;
}
