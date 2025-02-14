package homework.app.Enum;

import homework.app.Animal.Animal;
import homework.app.Animal.Monkey;
import homework.app.Animal.Rabbit;
import homework.app.Animal.Tiger;
import homework.app.Animal.Wolf;

/**
 * Enum для удобной работы с животными в основной программе
 * Также умеет строит экземпляры классов Monkey, Wolf, Tiger, Rabbit
 */
public enum  ZooObject {
    MONKEY("Monkey", true),
    RABBIT("Rabbit", true),
    TIGER("Tiger", false),
    WOLF("Wolf", false);

    final String title;
    final boolean isHerbo;
    ZooObject(String title, boolean isHerbo) {
        this.title = title;
        this.isHerbo = isHerbo;
    }

    /**
     * Красивое название объекта
     * @return название рода объекта
     */
    public String getTitle() {
        return this.title;
    }

    /**
     *
     * @return - true - травядное / false - не травоядное
     */
    public boolean getIsHerbo() {
        return isHerbo;
    }

    /**
     * Построит животное по соответствующему енаму
     * @param name - имя животного
     * @param age - возраст
     * @param food - количество потребляемой еды
     * @param kindness - добрата
     * @return класс соответствующего животного
     */
    public Animal buildSpecificAnimal(String name, int age, int food, int kindness) {
        return switch (this) {
            case MONKEY -> new Monkey(name, age, food, kindness);
            case RABBIT -> new Rabbit(name, age, food, kindness);
            case WOLF -> new Wolf(name, age, food);
            case TIGER -> new Tiger(name, age, food);
        };
    }

}
