package homework.app.Animal;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Herbo extends Animal {
    private final int kindness;
    public Herbo(String name, int age, int food, String invName, int kindness, int maxAge) {
        super(name, age, food, invName, maxAge);
        this.kindness = kindness;
    }
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
    public boolean isContact() {
        return kindness > 5;
    }

}
