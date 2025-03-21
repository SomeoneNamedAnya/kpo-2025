package org.example.commands.print_all;

import org.example.domains.BankAccount;
import org.example.interfaces.ICommand;
import org.example.services.BankAccountManager;

import java.util.Scanner;

public class PrintAccountCommand implements ICommand{
    private final BankAccountManager bankAccountManager;
    public PrintAccountCommand(BankAccountManager bankAccountManager) {
        this.bankAccountManager = bankAccountManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("BankAccount:");
            for (BankAccount item : bankAccountManager.getAllBankAccounts()) {
                System.out.println("----------------------");
                System.out.printf("id: %d\n", item.getId());
                System.out.printf("name: %s\n", item.getName());
                System.out.printf("balance: %f\n", item.getBalance());
            }
        }  catch (Exception e) {
            System.out.println("Что то пошле не так...");
        }

    }


}
