package org.example.commands.print_all;

import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.interfaces.ICommand;
import org.example.services.OperationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrintOperationCommand implements ICommand{
    private final OperationManager operationManager;
    public PrintOperationCommand(OperationManager operationManager) {
        this.operationManager = operationManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Category:");
            for (Operation item : operationManager.getAllOperation()) {
                System.out.println("----------------------");
                System.out.printf("id: %d\n", item.getId());
                System.out.printf("type: %s\n", item.getType());
                System.out.printf("bankAccountId: %d\n", item.getBankAccountId());
                System.out.printf("amount: %f\n", item.getAmount());
                System.out.printf("date: %s\n", item.getDate().toString());
                System.out.printf("categoryId: %d\n", item.getCategoryId());
                System.out.printf("description: %s\n", item.getDescription());

            }
        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }


}
