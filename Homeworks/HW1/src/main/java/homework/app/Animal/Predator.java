package homework.app.Animal;
/**
 * Класс хищьников
 */
public class Predator extends Animal {
    public Predator(String name, int age, int food, String invName, int maxAge) {
        super(name, age, food, invName, maxAge);
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
                        Food per day (kg): %d
                        """,
                numOfRecord,
                getInvNumber(),
                getInvName(),
                getAge(),
                getName(),
                getFood());
    }
    /**
     * Можно ли животное в контактный зоопарк
     * @return true - можно / false - нельзя
     */
    public boolean isContact() {
        return false;
    }
}
