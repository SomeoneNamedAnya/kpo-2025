package org.example.commands.balance;

import org.example.domains.Operation;
import org.example.interfaces.ICommand;
import org.example.services.Analytics;
import org.example.services.BalanceService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class RecalculateCommand implements ICommand {
    private final BalanceService balanceService;
    Scanner scan;
    public RecalculateCommand(BalanceService balanceService) {
        scan = new Scanner(System.in);
        this.balanceService = balanceService;
    }
    @Override
    public void execute() {
        try {
            System.out.println("Заполните информацию для запроса.");
            System.out.println("id интересующего аккаунта:");
            int id = scan.nextInt();
            scan.nextLine();

            float res = balanceService.recalculateBalance(id);

            System.out.printf("Пересчитанный баланс выбранного акккаунта: %f\n", res);

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }
    }
}
