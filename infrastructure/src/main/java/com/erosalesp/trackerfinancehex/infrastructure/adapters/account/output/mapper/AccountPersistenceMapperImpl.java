package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.mapper;

import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountPersistenceMapperImpl implements AccountPersistenceMapper{
    @Override
    public AccountEntity toAccountEntity(Account accountDomain) {
        if(accountDomain == null) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName(accountDomain.getAccountName());
        accountEntity.setCurrentBalance(accountDomain.getCurrentBalance());
        accountEntity.setInitialBalance(accountDomain.getInitialBalance());

        return accountEntity;
    }

    @Override
    public Account toAccountDomain(AccountEntity accountEntity) {
        if(accountEntity == null) {
            return null;
        }

        Account accountDomain = new Account();
        accountDomain.setId(accountEntity.getId());
        accountDomain.setAccountName(accountEntity.getAccountName());
        accountDomain.setCurrentBalance(accountEntity.getCurrentBalance());
        accountDomain.setInitialBalance(accountEntity.getInitialBalance());

        return accountDomain;
    }

    @Override
    public List<Account> toAccountDomainList(List<AccountEntity> accountEntityList) {

        return accountEntityList.stream()
                .map(accountEntity -> {
                    Account accountDomain = new Account();
                    accountDomain.setId(accountEntity.getId());
                    accountDomain.setAccountName(accountEntity.getAccountName());
                    accountDomain.setCurrentBalance(accountEntity.getCurrentBalance());
                    accountDomain.setInitialBalance(accountEntity.getInitialBalance());
                    return accountDomain;
                })
                .collect(Collectors.toList());
    }
}
