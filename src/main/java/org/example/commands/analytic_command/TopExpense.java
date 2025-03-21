package org.example.commands.analytic_command;

import org.example.auxiliary_tools.Pair;
import org.example.domains.BankAccount;
import org.example.domains.Operation;
import org.example.interfaces.ICommand;
import org.example.services.Analytics;
import org.example.services.BankAccountManager;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class TopExpense implements ICommand{
    private final Analytics analytics;
    Scanner scan;
    public TopExpense(Analytics analytics) {
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

            ArrayList<Pair> res = analytics.topExpense(id);
            int ind = 1;
            for(Pair item : res) {
                System.out.printf("%d) %s = %f\n", ind, item.getSecondValue(), item.getFirstValue());
                ind += 1;
            }

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }


    }


}
