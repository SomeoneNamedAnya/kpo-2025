package org.example.commands.refactor;

import org.example.interfaces.ICommand;
import org.example.services.OperationManager;

import java.util.Scanner;

public class RefactorOperationCommand implements ICommand{
    private final OperationManager operationManager;
    Scanner scan;
    public RefactorOperationCommand(OperationManager operationManager) {
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
            System.out.println("new description:");
            String newName = scan.nextLine();
            boolean res = operationManager.refactorOperation(id, newName);
            if (!res) {
                System.out.println("Не получилось изменить");
            } else {
                System.out.println("Изменено");
            }

        } catch (Exception e) {
            System.out.println("Не получилось считать данные");
        }

    }


}
