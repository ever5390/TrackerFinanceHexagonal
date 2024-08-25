package com.erosalesp.trackerfinancehex.application.account.ports.output;


import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {
    Account save(Account transaction);

    Optional<Account> findById(String idAccount);

    Account findByAccountName(String accountName);

    List<Account> findAll();

    void deleteById(String id);
}
