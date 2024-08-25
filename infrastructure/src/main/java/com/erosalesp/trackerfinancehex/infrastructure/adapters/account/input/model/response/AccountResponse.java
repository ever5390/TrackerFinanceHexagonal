package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.response;

import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private String id;
    private String accountName;
    private BigDecimal currentBalance;
    private BigDecimal initialBalance;
}
