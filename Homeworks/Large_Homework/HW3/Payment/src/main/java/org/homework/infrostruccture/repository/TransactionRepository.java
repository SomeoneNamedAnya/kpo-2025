package org.homework.infrostruccture.repository;

import jakarta.persistence.LockModeType;
import org.homework.domain.Account;
import org.homework.domain.TransactionalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionalInformation, Integer> {

    @Query("SELECT SUM(t.sum) FROM TransactionalInformation t WHERE t.accountId = :accountId")
    Optional<Float> getCurrentSum(@Param("accountId") int accountId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TransactionalInformation t WHERE accountId = :accountId")
    List<TransactionalInformation> lockByAccount(@Param("accountId") int accountId);
}
