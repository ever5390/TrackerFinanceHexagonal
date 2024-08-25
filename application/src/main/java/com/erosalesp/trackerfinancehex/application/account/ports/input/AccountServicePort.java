package com.erosalesp.trackerfinancehex.application.account.ports.input;


import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;

import java.util.List;

public interface AccountServicePort {

    Account save(Account account);

    Account findById(String idAccount);

    Account findByAccountName(String accountName);

    List<Account> findAll();

    Account update(String idAccount, Account account);

    void deleteById(String id);
}
