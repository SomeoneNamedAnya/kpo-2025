package homework.app.Animal;
import homework.app.Interface.IContact;
import homework.app.Interface.IInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import homework.app.Interface.IAlive;
import homework.app.Interface.IInventory;

/**
 *
 */
@Getter
public abstract class Animal implements IAlive, IInventory, IInfo, IContact {
    private final int maxAge;
    private final int food;
    private final String name;
    private final int age;
    private final String invName;
    @Setter
    private int health = -1;
    @Setter
    private int invNumber = -1;
    public Animal(String name, int age, int food, String invName, int maxAge) {
        this.name = name;
        this.age = age;
        this.food = food;
        this.invName = invName;
        this.maxAge = maxAge;
    }


}
