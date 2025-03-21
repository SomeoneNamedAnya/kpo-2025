package org.example.commands.analytic_command;

import org.example.domains.Operation;
import org.example.interfaces.ICommand;
import org.example.services.Analytics;
import org.example.services.CategoryManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class GroupByExpenseCat implements ICommand{
    private final Analytics analytics;
    Scanner scan;
    public GroupByExpenseCat(Analytics analytics) {
        scan = new Scanner(System.in);
        this.analytics = analytics;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию для запроса.");
            System.out.println("id интересующего аккаунта:");
            int id = scan.nextInt();
            scan.nextLine();

            Map<String, ArrayList<Operation>> res = analytics.groupByExpense(id);
            int ind = 1;
            for(String item : res.keySet()) {
                System.out.printf("%d) %s:\n", ind, item);
                ind += 1;
                for(Operation op : res.get(item)) {
                    op.print();
                }
            }

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }


}
