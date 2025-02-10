package hse.kpo.studying;
/**
 * Интерфейс с метадом boolean isCompatible(Customer customer)
 */
public interface IEngine {

    /**
     * Метод для проверки совместимости двигателя с покупателем.
     *
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true, если двигатель подходит покупателю
     */
    boolean isCompatible(Customer customer);
}
