package com.erosalesp.trackerfinancehex.domain.account.models.entity;


import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    private String id;
    private String accountName;
    private BigDecimal currentBalance;
    private BigDecimal initialBalance;
}
