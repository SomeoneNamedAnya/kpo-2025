package homework.app.Animal;

/**
 * Класс тигров
 * MaxAge можно было бы как нибудь использовать для определения здоровья (что то для несбыточного будущего)
 */
public class Tiger extends Predator {
    public Tiger(String name, int age, int food){
        super(name, age, food, "Tiger", 15);
    }
}
