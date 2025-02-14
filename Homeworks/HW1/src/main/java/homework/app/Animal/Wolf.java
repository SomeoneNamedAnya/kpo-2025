package homework.app.Animal;

/**
 * Класс волков
 * MaxAge можно было бы как нибудь использовать для определения здоровья (что то для несбыточного будущего)
 */
public class Wolf extends Predator {
    public Wolf(String name, int age, int food){
        super(name, age, food,"Wolf", 15);
    }
}
