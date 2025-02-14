package homework.app.Animal;

public class Predator extends Animal {
    public Predator(String name, int age, int weightFood, int invNumber, String invName, int maxAge) {
        super(name, age, weightFood, invNumber, invName, maxAge);
    }
    public void printInfo(int numOfRecord) {
        System.out.printf("%d. ----------- \nInventory number: %d \nAnimal species: %s \n Age: %d\n Name: %s\n Food per day (kg): %d\n",
                numOfRecord,
                getInvNumber(),
                getInvName(),
                getAge(),
                getName(),
                getWeightFood());
    }

    public boolean isContact() {
        return false;
    }
}
