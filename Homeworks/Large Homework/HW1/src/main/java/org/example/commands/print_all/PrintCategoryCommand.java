package org.example.commands.print_all;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.interfaces.ICommand;
import org.example.services.CategoryManager;

import java.util.Scanner;

public class PrintCategoryCommand implements ICommand{
    private final CategoryManager categoryManager;
    public PrintCategoryCommand(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Category:");
            for (Category item : categoryManager.getAllCategory()) {
                System.out.println("----------------------");
                System.out.printf("id: %d\n", item.getId());
                System.out.printf("name: %s\n", item.getName());
                System.out.printf("type: %s\n", item.getType());
            }
        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }


}
