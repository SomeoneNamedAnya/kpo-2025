package homework.app.Animal;

/**
 * Класс кроликов
 * MaxAge можно было бы как нибудь использовать для определения здоровья (что то для несбыточного будущего)
 */
public class Rabbit extends Herbo {
    public Rabbit(String name, int age, int food, int kindness) {
        super(name, age, food, "Rabbit", kindness, 10);

    }
}
