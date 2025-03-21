package org.example.commands.create;

import org.example.interfaces.ICommand;
import org.example.services.CategoryManager;
import org.example.services.OperationManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateOperationCommand implements ICommand{
    private final OperationManager operationManager;
    Scanner scan;
    public CreateOperationCommand(OperationManager operationManager) {
        scan = new Scanner(System.in);
        this.operationManager = operationManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию об операции.");

            System.out.println("type:");
            String type = scan.next();
            scan.nextLine();

            System.out.println("bankAccountId:");
            int bankAccountId = scan.nextInt();
            scan.nextLine();

            System.out.println("amount:");
            float amount = scan.nextFloat();
            scan.nextLine();

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("data (в формате yyyy-MM-dd HH:mm:ss):");
            String dataString = scan.nextLine();
            LocalDateTime date = LocalDateTime.parse(dataString, format);

            System.out.println("categoryId:");
            int categoryId = scan.nextInt();
            scan.nextLine();

            System.out.println("description:");
            String description = scan.nextLine();

            int id = operationManager.makeNewOperation(type, bankAccountId, amount, date, categoryId, description);
            if (id == -1) {
                System.out.println("Не удалось создать категорию");
            } else {
                System.out.printf("Создана категория с id = %d \n", id);
            }

        } catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
