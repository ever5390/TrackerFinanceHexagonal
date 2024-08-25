package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output;

import com.erosalesp.trackerfinancehex.application.account.ports.output.AccountPersistencePort;
import com.erosalesp.trackerfinancehex.domain.account.models.entity.Account;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.entity.AccountEntity;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.mapper.AccountPersistenceMapper;
import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountRepository accountRepository;

    private final AccountPersistenceMapper accountPersistenceMapper;

    public AccountPersistenceAdapter(AccountRepository accountRepository, AccountPersistenceMapper accountPersistenceMapper) {
        this.accountRepository = accountRepository;
        this.accountPersistenceMapper = accountPersistenceMapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountPersistenceMapper.toAccountEntity(account);
        return accountPersistenceMapper.toAccountDomain(accountRepository.save(accountEntity));
    }

    @Override
    public Optional<Account> findById(String idAccount) {
        return accountRepository.findById(idAccount).
                map(accountPersistenceMapper::toAccountDomain);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountPersistenceMapper.toAccountDomain(accountRepository.findByAccountName(accountName));
    }

    @Override
    public List<Account> findAll() {
        return accountPersistenceMapper.toAccountDomainList(accountRepository.findAll());
    }

    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }
}
