package homework.app.Animal;

public class Predator extends Animal {
    public Predator(String name, int age, int food, String invName, int maxAge) {
        super(name, age, food, invName, maxAge);
    }
    public void printInfo(int numOfRecord) {
        System.out.printf("%d. ----------- \nInventory number: %d \nAnimal species: %s \n Age: %d\n Name: %s\n Food per day (kg): %d\n",
                numOfRecord,
                getInvNumber(),
                getInvName(),
                getAge(),
                getName(),
                getFood());
    }

    public boolean isContact() {
        return false;
    }
}
