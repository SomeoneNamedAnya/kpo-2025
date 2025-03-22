package org.example.commands.delete;

import org.example.interfaces.ICommand;
import org.example.services.BankAccountManager;

import java.util.Scanner;

public class DeleteAccountCommand implements ICommand{
    private final BankAccountManager bankAccountManager;
    Scanner scan;
    public DeleteAccountCommand(BankAccountManager bankAccountManager) {
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
            boolean res = bankAccountManager.deleteBankAccounts(id);
            if (!res) {
                System.out.println("Не получилось удалить");
            } else {
                System.out.println("Удалено");
            }

        }  catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
