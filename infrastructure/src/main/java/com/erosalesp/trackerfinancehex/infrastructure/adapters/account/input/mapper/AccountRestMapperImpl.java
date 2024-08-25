package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.mapper;

import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.request.AccountCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.response.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AccountRestMapperImpl implements AccountRestMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Override
    public Account toAccountDomain(AccountCreateRequest accountCreateRequest) {
        if(accountCreateRequest == null) {
            return null;
        }

        Account account = new Account();
        account.setAccountName(accountCreateRequest.getAccountName());
        account.setCurrentBalance(accountCreateRequest.getCurrentBalance());

        return account;
    }

    @Override
    public AccountResponse toAccountResponse(Account accountDomain) {
        if(accountDomain == null) {
            return null;
        }
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(accountDomain.getId());
        accountResponse.setAccountName(accountDomain.getAccountName());
        accountResponse.setCurrentBalance(accountDomain.getCurrentBalance());
        accountResponse.setInitialBalance(accountDomain.getInitialBalance());

        return accountResponse;
    }

    @Override
    public List<AccountResponse> toAccountResponseList(List<Account> accountDomainList) {

        return accountDomainList.stream()
                .map(accountDomain -> {
                    AccountResponse accountResponse = new AccountResponse();
                    accountResponse.setId(accountDomain.getId());
                    accountResponse.setAccountName(accountDomain.getAccountName());
                    accountResponse.setCurrentBalance(accountDomain.getCurrentBalance());
                    accountResponse.setInitialBalance(accountDomain.getInitialBalance());
                    return accountResponse;
                })
                .collect(Collectors.toList());
    }
}
