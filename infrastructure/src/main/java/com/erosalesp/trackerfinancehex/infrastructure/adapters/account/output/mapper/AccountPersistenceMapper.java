package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.mapper;

import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.entity.AccountEntity;

import java.util.List;

public interface AccountPersistenceMapper {

    AccountEntity toAccountEntity(Account accountDomain);
    Account toAccountDomain(AccountEntity accountEntity);
    List<Account> toAccountDomainList(List<AccountEntity> accountEntityList);
}