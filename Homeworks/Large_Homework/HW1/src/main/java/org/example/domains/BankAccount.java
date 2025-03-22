package org.example.domains;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


public class BankAccount {
    @Getter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private float balance;

    public BankAccount(int id, String name, float balance) {
        this.id = id;
        this.balance = balance;
        this.name = name;
    }

    public void print() {
        System.out.printf("id = %d; name = %s; balance = %f\n",
                id, name, balance);
    }
}
