package org.example.services;

import org.example.domains.BankAccount;
import org.example.domains.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BalanceService {
    @Autowired
    OperationManager operationManager;

    @Autowired
    BankAccountManager bankAccountManager;

    public float getBalance(int id) {
        List<BankAccount> curBankAccount = bankAccountManager.getAllBankAccounts()
                .stream().filter(x -> x.getId() == id).toList();
        return curBankAccount.get(0).getBalance();
    }

    public float recalculateBalance(int id) {
        List<BankAccount> curBankAccount = bankAccountManager.getAllBankAccounts()
                .stream().filter(x -> x.getId() == id).toList();
        List<Operation> curOperation = operationManager.getAllOperation()
                .stream().filter(x -> x.getBankAccountId() == id).toList();
        float actualBalance = 0;

        for (Operation x : curOperation) {
            if (x.getType().equals("income")) {
                actualBalance += x.getAmount();
            } else {
                actualBalance -= x.getAmount();
            }
        }
        curBankAccount.get(0).setBalance(actualBalance);
        return actualBalance;
    }
}
