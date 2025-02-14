package homework.app.Interface;
/**
 * Интерфейс реализующися всеми объектами которые стоят на учете у зоопарка
 */
public interface IInventory {
    /**
     * Инвентаризационный номер объекта
     * @return номер объекта
     */
    public int getInvNumber();
    /**
     * Инвентаризационное название объекта (в случае животных это вид животного, например Monkey
     * у вещей будет название предмета, например Computer)
     * @return название объекта
     */
    public String getInvName();

}
