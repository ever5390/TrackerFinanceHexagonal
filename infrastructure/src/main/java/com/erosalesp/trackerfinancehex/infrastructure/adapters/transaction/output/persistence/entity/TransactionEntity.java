package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.entity;


import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.BalanceFlow;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.OperationType;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.entity.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private LocalDateTime createAt;

    private String codeTransfer;
    private String description;
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @Enumerated(EnumType.STRING)
    private BalanceFlow BalanceFlow;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;
}
