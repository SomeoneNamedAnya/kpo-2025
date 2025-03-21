package org.example.commands.create;

import org.example.interfaces.ICommand;
import org.example.services.BankAccountManager;
import org.example.services.CategoryManager;

import java.util.Scanner;

public class CreateCategoryCommand implements ICommand{
    private final CategoryManager categoryManager;
    Scanner scan;
    public CreateCategoryCommand(CategoryManager categoryManager) {
        scan = new Scanner(System.in);
        this.categoryManager = categoryManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию о категории.");
            System.out.println("name:");
            String name = scan.nextLine();
            System.out.println("type:");
            String type = scan.next();
            scan.nextLine();
            int id = categoryManager.makeNewCategory(name, type);

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
