package homework.app.Animal;
import homework.app.Interface.IContact;
import homework.app.Interface.IInfo;
import lombok.Getter;
import lombok.ToString;
import homework.app.Interface.IAlive;
import homework.app.Interface.IInventory;

/**
 *
 */
@Getter
@ToString
public abstract class Animal implements IAlive, IInventory, IInfo, IContact {
    private final int maxAge;
    private final int weightFood;
    private final String name;
    private final int age;
    private final int invNumber;
    private final String invName;
    public Animal(String name, int age, int weightFood, int invNumber, String invName, int maxAge) {
        this.name = name;
        this.age = age;
        this.weightFood = weightFood;
        this.invName = invName;
        this.invNumber = invNumber;
        this.maxAge = maxAge;
    }

}
