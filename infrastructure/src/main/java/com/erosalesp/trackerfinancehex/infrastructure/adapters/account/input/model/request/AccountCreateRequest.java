package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountCreateRequest {

    @NotBlank(message = "El nombre de la cuenta no puede ser vacío")
    private String accountName;

    @NotNull(message = "El saldo de la cuenta no puede estar vacío")
    @Min(value = 1 , message = "El saldo debe ser mayor a 0")
    private BigDecimal currentBalance;

}
