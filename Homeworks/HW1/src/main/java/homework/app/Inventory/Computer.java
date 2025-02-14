package homework.app.Inventory;

public class Computer extends Thing{
    public Computer(int invNumber, String invName) {
        super(invNumber, "Computer");
    }

    @Override
    public void printInfo(int numOfRecord) {
        System.out.println("Компьютер");
    }
}
