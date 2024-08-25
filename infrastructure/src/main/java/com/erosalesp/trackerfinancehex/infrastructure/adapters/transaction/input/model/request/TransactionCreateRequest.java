package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.input.model.request;

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
public class TransactionCreateRequest {

    private LocalDateTime createAt;

    @NotBlank(message = "Description field cannot be empty or null")
    private String description;

    @NotNull(message = "Amount field cannot be null")
    @Min(value = 1, message = "Amount field cannot be zero or negative")
    private BigDecimal amount;

    @NotNull(message = "Account origin field cannot be null")
    private Account account;

    @NotNull(message = "Account destiny field cannot be null")
    private Account accountDestiny;

    @NotNull(message = "Operation finance cannot be null")
    @NotBlank(message = "Operation field cannot be empty or null")
    private BalanceFlow BalanceFlow;

    @NotNull(message = "Operation type field cannot be null")
    @NotBlank(message = "Operation type field cannot be empty or null")
    private OperationType operationType;
}
