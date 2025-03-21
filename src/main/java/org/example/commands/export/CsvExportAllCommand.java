package org.example.commands.export;

import org.example.interfaces.ICommand;
import org.example.interfaces.IManager;
import org.example.params.ReportType;
import org.example.services.BankAccountManager;
import org.example.services.CategoryManager;
import org.example.services.OperationManager;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;

public class CsvExportAllCommand implements ICommand {
    private final BankAccountManager bankAccountManager;
    private final CategoryManager categoryManager;
    private final OperationManager operationManager;
    Scanner scan;
    public CsvExportAllCommand(BankAccountManager bankAccountManager,
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

            CsvExportCommand account = new CsvExportCommand(bankAccountManager, filenameAccount);
            CsvExportCommand category = new CsvExportCommand(categoryManager, filenameCategory);
            CsvExportCommand operation = new CsvExportCommand(operationManager, filenameOperation);

            account.execute();
            category.execute();
            operation.execute();

            System.out.println("Информация сохранена");

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }
}
