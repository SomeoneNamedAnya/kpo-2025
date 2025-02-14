package homework;

import homework.app.Zoo.Zoo;

import homework.app.Animal.AnimalExample.Monkey;
import homework.app.Animal.AnimalExample.Rabbit;
import homework.app.Animal.AnimalExample.Tiger;
import homework.app.Animal.AnimalExample.Wolf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;


@SpringBootApplication
public class ERS {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ERS.class, args);
        Scanner scan = new Scanner(System.in);
        Zoo zoo = context.getBean(Zoo.class);
        boolean isWorked = true;
        int invNum = 1;

        while(isWorked) {

            System.out.println("Меню:");
            System.out.println("1) Добавить новое животное");
            System.out.println("2) Посмотреть отчет о потребляемых килограммах еды");
            System.out.println("3) Посмотреть сисок животных пригодных для контактного зоопарка");
            System.out.println("4) Закончить работу");

            int action = scan.nextInt();

            switch (action) {
                case 1 -> {
                    System.out.println("Добавление нового животного:");
                    System.out.println("1) Заполните информацию о новом животном");
                    System.out.print("   Вид: ");
                    String invName = scan.next();
                    System.out.print("   Возраст: ");
                    int age = scan.nextInt();
                    System.out.print("   Имя: ");
                    String name = scan.next();
                    System.out.print("   Сколько ест кг в день: ");
                    int food = scan.nextInt();
                    boolean isAnimalExist = true;
                    boolean isAnimalAccept = true;

                    switch (invName) {
                        case "Monkey" -> {
                            System.out.print("   Уровень доброты: ");
                            int kindness = scan.nextInt();
                            isAnimalAccept = zoo.addAnimal(new Monkey(name, age, food, invNum, kindness));
                        }
                        case "Rabbit" -> {
                            System.out.print("   Уровень доброты: ");
                            int kindness = scan.nextInt();
                            isAnimalAccept = zoo.addAnimal(new Rabbit(name, age, food, invNum, kindness));
                        }
                        case "Tiger" -> {
                            isAnimalAccept = zoo.addAnimal(new Tiger(name, age, food, invNum));
                        }
                        case "Wolf" -> {
                            isAnimalAccept = zoo.addAnimal(new Wolf(name, age, food, invNum));
                        }
                        default -> {
                            isAnimalExist = false;
                            System.out.println("Пока что такого животного не существует");
                        }
                    }
                    if (isAnimalExist) {
                        System.out.println("2) Данные записаны и направлены в ветеренарную клинику");
                        System.out.print("3) Ответ ветеренарной клиники: ");
                        if (isAnimalAccept) {
                            System.out.println("Животное принято");
                            invNum += 1;
                        } else {
                            System.out.println("Отказано");
                        }
                    }
                }
                case 2 -> {
                    zoo.makeAnimalReport();
                }
                case 3 -> {
                    zoo.makeHerboList();
                }
                case 4 -> {
                    isWorked = false;
                }
            }

        }

    }

}
