package homework.app;

import homework.app.Enum.ZooObject;
import homework.app.Zoo.Zoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.*;


@SpringBootApplication
public class ERS {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ERS.class, args);
        Scanner scan = new Scanner(System.in);
        Zoo zoo = context.getBean(Zoo.class);
        boolean isWorked = true;

        while(isWorked) {


            System.out.println("Меню:");
            System.out.println("1) Добавить новое животное");
            System.out.println("2) Посмотреть отчет о потребляемой животнами еде");
            System.out.println("3) Посмотреть сисок животных пригодных для контактного зоопарка");
            System.out.println("4) Посмотреть сисок всего инвентаря");
            System.out.println("5) Закончить работу");

            int action = -1;
            try {
                action = scan.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Неверный формат данных (требуется натуральное число)." +
                        " Попробуйте снова.");
                scan.nextLine();
            }

            switch (action) {
                case 1 -> {
                    ZooObject newAnimal;
                    System.out.println("Добавление нового животного:");
                    System.out.println("Зоопарк принимает следующих животных (в поле вид укажите одно из них):");
                    Arrays.stream(ZooObject.values()).forEach(type -> System.out.printf("%s\n", type.getTitle()));
                    System.out.println("1) Заполните информацию о новом животном");
                    System.out.print("   Вид: ");
                    String invName = scan.next();

                    try {
                        newAnimal = ZooObject.valueOf(invName.toUpperCase(Locale.ROOT));
                    } catch (IllegalArgumentException error) {
                        System.out.println("Таких животных не принимаем!");
                        continue;
                    }

                    int age = -1;
                    int food = -1;
                    int kindness = -1;
                    String name;

                    while (true) {
                        System.out.print("   Возраст: ");
                        try {
                            age = scan.nextInt();
                            if (age > 0) break;
                            else {
                                System.out.println("Неверный формат данных (требуется натуральное число)." +
                                        " Попробуйте снова.");
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("Неверный формат данных (требуется натуральное число)." +
                                    " Попробуйте снова.");
                            scan.nextLine();
                        }
                    }

                    System.out.print("   Имя: ");
                    name = scan.next();
                    while (true) {
                        System.out.print("   Сколько ест кг в день: ");
                        try {
                            food = scan.nextInt();
                            if (food > 0) break;
                            else {
                                System.out.println("Неверный формат данных (требуется натуральное число)." +
                                        " Попробуйте снова.");
                            }
                        } catch (NoSuchElementException error) {
                            System.out.println("Неверный формат данных (требуется натуральное число)." +
                                    " Попробуйте снова.");
                            scan.nextLine();
                        }
                    }

                    if (newAnimal.getIsHerbo()) {
                        while (true) {
                            try {
                                System.out.print("   Уровень доброты: ");
                                kindness = scan.nextInt();
                                if (kindness >= 0 && kindness <= 10) break;
                                else {
                                    System.out.println("Неверный формат данных (требуется целое число от 0 до 10)." +
                                            " Попробуйте снова.");
                                }
                            } catch (NoSuchElementException error) {
                                System.out.println("Неверный формат данных (требуется целое число от 0 до 10)." +
                                        " Попробуйте снова.");
                                scan.nextLine();
                            }
                        }
                    }

                    boolean isAnimalAccept = zoo.addAnimal(newAnimal.buildSpecificAnimal(name, age, food, kindness));

                    System.out.println("2) Данные записаны и направлены в ветеренарную клинику");
                    System.out.print("3) Ответ ветеренарной клиники: ");

                    if (isAnimalAccept) {
                        System.out.println("Животное принято");
                    } else {
                        System.out.println("Отказано");
                    }
                }
                case 2 -> {
                    zoo.makeAnimalFoodReport();
                }
                case 3 -> {
                    zoo.makeContactAnimalReport();
                }
                case 4 -> {
                    zoo.makeGeneralReport();
                }
                case 5 -> {
                    isWorked = false;
                }
                default -> {
                    System.out.println("Такой команды нет");
                }
            }

        }

    }

}
