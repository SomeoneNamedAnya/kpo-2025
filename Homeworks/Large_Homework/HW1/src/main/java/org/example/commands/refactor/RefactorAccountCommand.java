package org.example.commands.refactor;

import org.example.interfaces.ICommand;
import org.example.services.BankAccountManager;

import java.util.Scanner;

public class RefactorAccountCommand implements ICommand{
    private final BankAccountManager bankAccountManager;
    Scanner scan;
    public RefactorAccountCommand(BankAccountManager bankAccountManager) {
        scan = new Scanner(System.in);
        this.bankAccountManager = bankAccountManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию об аккаунте.");
            System.out.println("id:");
            int id = scan.nextInt();
            scan.nextLine();
            System.out.println("new name:");
            String newName = scan.nextLine();
            boolean res = bankAccountManager.refactorBankAccounts(id, newName);
            if (!res) {
                System.out.println("Не получилось изменить");
            } else {
                System.out.println("Изменено");
            }

        }  catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
