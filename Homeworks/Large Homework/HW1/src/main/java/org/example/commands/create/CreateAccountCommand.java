package org.example.commands.create;

import org.example.interfaces.ICommand;
import org.example.params.ObjectType;
import org.example.services.BankAccountManager;

import java.util.Scanner;

public class CreateAccountCommand implements ICommand{
    private final BankAccountManager bankAccountManager;
    Scanner scan;
    public CreateAccountCommand(BankAccountManager bankAccountManager) {
        scan = new Scanner(System.in);
        this.bankAccountManager = bankAccountManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию об аккаунте.");
            System.out.println("name:");
            String name = scan.nextLine();
            int id = bankAccountManager.makeNewBankAccount(name, 0);
            if (id == -1) {
                System.out.println("Не удалось создать аккаунт");
            } else {
                System.out.printf("Создан аккаунт с id = %d \n", id);
            }
        }  catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
