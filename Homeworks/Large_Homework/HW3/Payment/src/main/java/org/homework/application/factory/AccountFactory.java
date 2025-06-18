package org.homework.application.factory;

import org.homework.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {
    public Account create(int idUser) {
        return new Account(idUser);
    }
}