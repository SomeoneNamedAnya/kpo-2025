package homework.app.Inventory;

public class Table extends Thing {
    public Table(int invNumber, String invName) {
        super(invNumber, "Table");
    }

    @Override
    public void printInfo(int numOfRecord) {
        System.out.println("Стол");
    }
}
