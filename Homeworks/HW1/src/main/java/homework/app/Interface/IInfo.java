package homework.app.Interface;

/**
 * Интерфейс реализующися всеми объектами о которых можно напечатаь информацию
 */
public interface IInfo {
    /**
     * Печатает информацию о объекте
     * @param numOfRecord - номер записи (так как каждая запись при печати нумеруется
     */
    public void printInfo(int numOfRecord);
}
