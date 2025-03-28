package homework.app.Animal;

import lombok.Getter;

/**
 * Класс травоядных, дополнительно в конструкторе принимает доброту животного
 */
@Getter
public class Herbo extends Animal {
    private final int kindness;
    public Herbo(String name, int age, int food, String invName, int kindness, int maxAge) {
        super(name, age, food, invName, maxAge);
        this.kindness = kindness;
    }

    /**
     * Информация о соответствующем травоядном
     * @param numOfRecord - номер записи (так как каждая запись при печати нумеруется)
     */
    public void printInfo(int numOfRecord) {
        System.out.printf("""
                        %d. -----------\s
                        Inventory number: %d\s
                        Animal species: %s\s
                        Age: %d
                        Name: %s
                        Kindness: %d
                        Food per day (kg): %d
                        """,
                numOfRecord,
                getInvNumber(),
                getInvName(),
                getAge(),
                getName(),
                getKindness(),
                getFood());
    }

    /**
     * Можно ли животное в контактный зоопарк
     * @return true - можно / false - нельзя
     */
    public boolean isContact() {
        return kindness > 5;
    }

}
