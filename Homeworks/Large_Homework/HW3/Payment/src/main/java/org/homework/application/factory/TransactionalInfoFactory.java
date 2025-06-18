package org.homework.application.factory;

import org.homework.domain.Account;
import org.homework.domain.TransactionType;
import org.homework.domain.TransactionalInformation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionalInfoFactory {
    public TransactionalInformation create(int idAccount, float sum, TransactionType type) {
        return new TransactionalInformation(idAccount, sum, type.name(), LocalDateTime.now());
    }
}