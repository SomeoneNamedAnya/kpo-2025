package org.example.factories;

import org.example.domains.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountFactory {
    private int id = 0;
    public BankAccount create(String name, float balance) {
        id += 1;
        return new BankAccount(id, name, balance);
    }
    public BankAccount create(int selfId, String name, float balance) {
        id += 1;
        if (selfId < id) {
            id -= 1;
            return null;
        }
        id = selfId;
        return new BankAccount(id, name, balance);
    }
}
