package homework.app.Inventory;
/**
 * Класс компьютеров (для будущего из условия)
 */
public class Computer extends Thing{
    public Computer(int invNumber) {
        super(invNumber, "Computer");
    }
    /**
     * Информация о компьютере
     * @param numOfRecord - номер записи (так как каждая запись при печати нумеруется)
     */
    @Override
    public void printInfo(int numOfRecord) {
        System.out.println("Компьютер");
    }
}
