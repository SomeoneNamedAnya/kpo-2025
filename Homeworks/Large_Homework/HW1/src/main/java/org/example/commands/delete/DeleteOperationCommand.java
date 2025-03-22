package org.example.commands.delete;

import org.example.interfaces.ICommand;
import org.example.services.OperationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DeleteOperationCommand implements ICommand{
    private final OperationManager operationManager;
    Scanner scan;
    public DeleteOperationCommand(OperationManager operationManager) {
        scan = new Scanner(System.in);
        this.operationManager = operationManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию об операции.");
            System.out.println("id:");
            int id = scan.nextInt();
            scan.nextLine();
            boolean res = operationManager.deleteOperation(id);
            if (!res) {
                System.out.println("Не получилось удалить");
            } else {
                System.out.println("Удалено");
            }

        } catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
