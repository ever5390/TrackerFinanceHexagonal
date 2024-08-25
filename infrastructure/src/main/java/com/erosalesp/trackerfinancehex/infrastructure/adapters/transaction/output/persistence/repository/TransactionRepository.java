package com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.repository;

import com.erosalesp.trackerfinancehex.infrastructure.adapters.transaction.output.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
}
