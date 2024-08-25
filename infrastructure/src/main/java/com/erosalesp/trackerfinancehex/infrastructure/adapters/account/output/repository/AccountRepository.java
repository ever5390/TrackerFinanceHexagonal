package com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.repository;

import com.erosalesp.trackerfinancehex.infrastructure.adapters.account.output.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Query("SELECT a FROM AccountEntity a WHERE LOWER(a.accountName) = LOWER(:accountName)")
    AccountEntity findByAccountName(@Param("accountName") String accountName);
}
