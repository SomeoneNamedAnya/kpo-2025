package homework.app.Inventory;
/**
 * Класс столов (для будущего из условия)
 */
public class Table extends Thing {
    public Table(int invNumber) {
        super(invNumber, "Table");
    }

    /**
     * Информация о столе
     * @param numOfRecord - номер записи (так как каждая запись при печати нумеруется)
     */
    @Override
    public void printInfo(int numOfRecord) {
        System.out.println("Стол");
    }
}
