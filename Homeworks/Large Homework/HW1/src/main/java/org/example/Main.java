package org.example;


import org.example.commands.Decorator;
import org.example.commands.analytic_command.*;
import org.example.commands.balance.RecalculateCommand;
import org.example.commands.create.CreateAccountCommand;
import org.example.commands.create.CreateCategoryCommand;
import org.example.commands.create.CreateOperationCommand;
import org.example.commands.export.CsvExportAllCommand;
import org.example.commands.export.JsonExportAllCommand;
import org.example.commands.export.YamlExportAllCommand;
import org.example.commands.print_all.PrintAccountCommand;
import org.example.commands.print_all.PrintCategoryCommand;
import org.example.commands.print_all.PrintOperationCommand;
import org.example.date_base.Cache;
import org.example.importer.AbstractImporter;
import org.example.importer.CsvImporter;
import org.example.importer.JsonImporter;
import org.example.importer.YamlImporter;
import org.example.interfaces.ICommand;
import org.example.interfaces.IConnection;
import org.example.params.ReportType;
import org.example.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileWriter;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.example.params.ReportType.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            ApplicationContext context = SpringApplication.run(Main.class, args);
            CategoryManager categoryManager = context.getBean(CategoryManager.class);
            BankAccountManager bankAccountManager = context.getBean(BankAccountManager.class);
            OperationManager operationManager = context.getBean(OperationManager.class);
            Analytics analytics = context.getBean(Analytics.class);
            BalanceService balanceService = context.getBean(BalanceService.class);
            IConnection connection = context.getBean(Cache.class);

            bankAccountManager.setConnection(connection);
            categoryManager.setConnection(connection);
            operationManager.setConnection(connection);


            Scanner scan = new Scanner(System.in);
            boolean isDB = false;
            boolean isStart = false;
            boolean isWorked = true;
            while (!isStart) {
                try {

                    System.out.println("Перед началом работы:");
                    System.out.println("1) Импортировать данные из csv");
                    System.out.println("2) Импортировать данные из json");
                    System.out.println("3) Импортировать данные из yaml");
                    System.out.println("4) Работать с внутренней базой данных (все изменения сохранятся по завершении работы)");
                    System.out.println("5) Начать работу без ничего");
                    System.out.println("6) Завершить работу");

                    int preAction = -1;
                    preAction = scan.nextInt();

                    switch (preAction) {
                        case 1 -> {

                            System.out.println("Заполните информацию об операции.");

                            System.out.println("имя для accounts (указывать с расширением): ");
                            String filenameAccount = scan.next();
                            bankAccountManager.importDate(new CsvImporter(), filenameAccount);

                            System.out.println("имя для category (указывать с расширением): ");
                            String filenameCategory = scan.next();
                            categoryManager.importDate(new CsvImporter(), filenameCategory);

                            System.out.println("имя для operation (указывать с расширением): ");
                            String filenameOperation = scan.next();
                            operationManager.importDate(new CsvImporter(), filenameOperation);

                            isStart = true;

                        }
                        case 2 -> {

                            System.out.println("Заполните информацию об операции.");

                            System.out.println("имя для accounts (указывать с расширением): ");
                            String filenameAccount = scan.next();

                            System.out.println("имя для category (указывать с расширением): ");
                            String filenameCategory = scan.next();

                            System.out.println("имя для operation (указывать с расширением): ");
                            String filenameOperation = scan.next();

                            bankAccountManager.importDate(new JsonImporter(), filenameAccount);
                            categoryManager.importDate(new JsonImporter(), filenameCategory);
                            operationManager.importDate(new JsonImporter(), filenameOperation);
                            isStart = true;

                        }
                        case 3 -> {

                            System.out.println("Заполните информацию об операции.");

                            System.out.println("имя для accounts (указывать с расширением): ");
                            String filenameAccount = scan.next();

                            System.out.println("имя для category (указывать с расширением): ");
                            String filenameCategory = scan.next();

                            System.out.println("имя для operation (указывать с расширением): ");
                            String filenameOperation = scan.next();

                            bankAccountManager.importDate(new YamlImporter(), filenameAccount);
                            categoryManager.importDate(new YamlImporter(), filenameCategory);
                            operationManager.importDate(new YamlImporter(), filenameOperation);
                            isStart = true;

                        }
                        case 4 -> {
                            connection.connectToDatabase("database.properties");
                            isDB = true;
                            isStart = true;
                        }

                        case 5 -> {
                            isStart = true;
                        }
                        case 6 -> {
                            isStart = true;
                            isWorked = false;
                        }

                        default -> {
                            System.out.println("Такой команды нет");
                        }

                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    System.out.println("Что то пошло не так...");
                }

            }


            while(isWorked) {


                System.out.println("Меню:");

                System.out.println("1) Создать новый аккаунт");
                System.out.println("2) Создать новую категорию");
                System.out.println("3) Создать новую операцию");

                System.out.println("4) Посмотреть сисок всех аккаунтов");
                System.out.println("5) Посмотреть сисок всех категорий");
                System.out.println("6) Посмотреть сисок всех операций");

                System.out.println("7) Посчитать разницу доходов расходов аккаунта за заданный период");
                System.out.println("8) Посмотреть сисок всех доходов аккаунта по категориям");
                System.out.println("9) Посмотреть сисок всех расходов аккаунта по категориям");
                System.out.println("10) Сгруппировать все доходы аккаунта по убыванию");
                System.out.println("11) Сгруппировать все расходы аккаунта по убыванию");

                System.out.println("12) Пересчитать баланс на заданном аккаунте");

                System.out.println("13) Экспортироват все данные в csv");
                System.out.println("14) Экспортироват все данные в json");
                System.out.println("15) Экспортироват все данные в yaml");

                System.out.println("16) Завершить работу");

                int action = -1;
                try {
                    action = scan.nextInt();
                } catch (NoSuchElementException e) {
                    System.out.println("Неверный формат данных (требуется натуральное число)." +
                            " Попробуйте снова.");
                    scan.nextLine();
                }
                ICommand curCommand = null;

                switch (action) {
                    case 1 -> {
                        curCommand = new Decorator(new CreateAccountCommand(bankAccountManager));
                    }
                    case 2 -> {
                        curCommand = new Decorator(new CreateCategoryCommand(categoryManager));
                    }
                    case 3 -> {
                        curCommand = new Decorator(new CreateOperationCommand(operationManager));
                    }
                    case 4 -> {
                        curCommand = new Decorator(new PrintAccountCommand(bankAccountManager));
                    }
                    case 5 -> {
                        curCommand = new Decorator(new PrintCategoryCommand(categoryManager));
                    }
                    case 6 -> {
                        curCommand = new Decorator(new PrintOperationCommand(operationManager));
                    }
                    case 7 -> {
                        curCommand = new Decorator(new DiffCommand(analytics));
                    }
                    case 8 -> {
                        curCommand = new Decorator(new GroupByIncomeCat(analytics));
                    }
                    case 9 -> {
                        curCommand = new Decorator(new GroupByExpenseCat(analytics));
                    }
                    case 10 -> {
                        curCommand = new Decorator(new TopIncome(analytics));
                    }
                    case 11 -> {
                        curCommand = new Decorator(new TopExpense(analytics));
                    }
                    case 12 -> {
                        curCommand = new Decorator(new RecalculateCommand(balanceService));
                    }
                    case 13 -> {
                        curCommand = new Decorator(new CsvExportAllCommand(bankAccountManager, categoryManager, operationManager));
                    }
                    case 14 -> {
                        curCommand = new Decorator(new JsonExportAllCommand(bankAccountManager, categoryManager, operationManager));
                    }
                    case 15 -> {
                        curCommand = new Decorator(new YamlExportAllCommand(bankAccountManager, categoryManager, operationManager));
                    }
                    case 16 -> {
                        isWorked = false;
                    }
                    default -> {
                        System.out.println("Такой команды нет");
                    }
                }
                if (curCommand != null) {
                    curCommand.execute();
                }


            }

            if (isDB) {
                bankAccountManager.saveAll();
                categoryManager.saveAll();
                operationManager.saveAll();
            }

        } catch (Exception e) {
            System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
        }

    }


}