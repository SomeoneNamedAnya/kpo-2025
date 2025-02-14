package homework.app.Enum;

import homework.app.Animal.Animal;
import homework.app.Animal.AnimalExample.Monkey;
import homework.app.Animal.AnimalExample.Rabbit;
import homework.app.Animal.AnimalExample.Tiger;
import homework.app.Animal.AnimalExample.Wolf;

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
    public String getTitle() {
        return this.title;
    }

    public boolean getIsHerbo() {
        return isHerbo;
    }

    public Animal buildSpecificAnimal(String name, int age, int food, int kindness) {
        return switch (this) {
            case MONKEY -> new Monkey(name, age, food, kindness);
            case RABBIT -> new Rabbit(name, age, food, kindness);
            case WOLF -> new Wolf(name, age, food);
            case TIGER -> new Tiger(name, age, food);
        };
    }

}
