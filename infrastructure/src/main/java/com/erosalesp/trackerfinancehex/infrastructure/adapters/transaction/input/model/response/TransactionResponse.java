package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.response;

import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.BalanceFlow;
import com.erosalesp.trackerfinancehex.domain.transaction.models.enums.OperationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private String id;

    private LocalDateTime createAt;

    private String description;

    private BigDecimal amount;

    private Account account;

    private BalanceFlow BalanceFlow;

    private OperationType operationType;
}
