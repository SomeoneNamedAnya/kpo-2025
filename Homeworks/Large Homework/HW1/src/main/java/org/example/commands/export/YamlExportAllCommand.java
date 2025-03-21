package org.example.commands.export;

import org.example.interfaces.ICommand;
import org.example.services.BankAccountManager;
import org.example.services.CategoryManager;
import org.example.services.OperationManager;

import java.util.Scanner;

public class YamlExportAllCommand implements ICommand {
    private final BankAccountManager bankAccountManager;
    private final CategoryManager categoryManager;
    private final OperationManager operationManager;
    Scanner scan;
    public YamlExportAllCommand(BankAccountManager bankAccountManager,
                                CategoryManager categoryManager,
                                OperationManager operationManager) {
        scan = new Scanner(System.in);
        this.bankAccountManager = bankAccountManager;
        this.categoryManager = categoryManager;
        this.operationManager = operationManager;
    }
    @Override
    public void execute() {
        try {

            System.out.println("Заполните информацию об операции.");

            System.out.println("имя для accounts (указывать с расширением): ");
            String filenameAccount = scan.next();
            scan.nextLine();

            System.out.println("имя для category (указывать с расширением): ");
            String filenameCategory = scan.next();
            scan.nextLine();

            System.out.println("имя для operation (указывать с расширением): ");
            String filenameOperation = scan.next();
            scan.nextLine();

            YamlExportCommand account = new YamlExportCommand(bankAccountManager, filenameAccount);
            YamlExportCommand category = new YamlExportCommand(categoryManager, filenameCategory);
            YamlExportCommand operation = new YamlExportCommand(operationManager, filenameOperation);

            account.execute();
            category.execute();
            operation.execute();

            System.out.println("Информация сохранена");

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }
}
