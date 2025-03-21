package org.example.commands.analytic_command;

import org.example.interfaces.ICommand;
import org.example.services.Analytics;
import org.example.services.BankAccountManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DiffCommand implements ICommand{
    private final Analytics analytics;
    Scanner scan;
    public DiffCommand(Analytics analytics) {
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
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.print("Начальная дата (в формате yyyy-MM-dd HH:mm:ss): ");
            String dataString = scan.nextLine();
            LocalDateTime dateStart = LocalDateTime.parse(dataString, format);

            System.out.print("Конечная дата (в формате yyyy-MM-dd HH:mm:ss): ");
            dataString = scan.nextLine();
            LocalDateTime dateEnd = LocalDateTime.parse(dataString, format);

            float res = analytics.getDifference(id, dateStart, dateEnd);

            System.out.printf("Доход минус расход за выбранный промежуток времени: %f\n", res);

        } catch (Exception e) {
            System.out.println("Что то пошло не так...");
        }

    }


}
