package homework.app.Animal;

import lombok.Getter;
@Getter
public class Herbo extends Animal {
    private final int kindness;
    public Herbo(String name, int age, int weightFood, int invNumber, String invName, int kindness, int maxAge) {
        super(name, age, weightFood, invNumber, invName, maxAge);
        this.kindness = kindness;
    }
    public void printInfo(int numOfRecord) {
        System.out.printf("%d. ----------- \nInventory number: %d \nAnimal species: %s \nAge: %d\nName: %s\nKindness: %d\nFood per day (kg): %d\n",
                numOfRecord,
                getInvNumber(),
                getInvName(),
                getAge(),
                getName(),
                getKindness(),
                getWeightFood());
    }
    public boolean isContact() {
        return kindness > 5;
    }

}
