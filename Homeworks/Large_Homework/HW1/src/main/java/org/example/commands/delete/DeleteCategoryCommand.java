package org.example.commands.delete;

import org.example.interfaces.ICommand;
import org.example.services.CategoryManager;

import java.util.Scanner;

public class DeleteCategoryCommand implements ICommand{
    private final CategoryManager categoryManager;
    Scanner scan;
    public DeleteCategoryCommand(CategoryManager categoryManager) {
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
            boolean res = categoryManager.deleteCategory(id);
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
