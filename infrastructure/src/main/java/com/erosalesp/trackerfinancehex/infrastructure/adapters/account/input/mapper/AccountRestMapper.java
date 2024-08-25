package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.mapper;


import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.request.AccountCreateRequest;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.input.model.response.AccountResponse;

import java.util.List;

public interface AccountRestMapper {

    Account toAccountDomain(AccountCreateRequest accountCreateRequest);

    AccountResponse toAccountResponse(Account accountDomain);

    List<AccountResponse> toAccountResponseList(List<Account> accountList);


}
