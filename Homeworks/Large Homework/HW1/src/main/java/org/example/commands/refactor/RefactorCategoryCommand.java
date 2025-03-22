package org.example.commands.refactor;

import org.example.interfaces.ICommand;
import org.example.services.CategoryManager;

import java.util.Scanner;

public class RefactorCategoryCommand implements ICommand{
    private final CategoryManager categoryManager;
    Scanner scan;
    public RefactorCategoryCommand(CategoryManager categoryManager) {
        scan = new Scanner(System.in);
        this.categoryManager = categoryManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию о категории.");
            System.out.println("id:");
            int id = scan.nextInt();
            scan.nextLine();
            System.out.println("new name:");
            String newName = scan.nextLine();
            boolean res = categoryManager.refactorCategory(id, newName);
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
