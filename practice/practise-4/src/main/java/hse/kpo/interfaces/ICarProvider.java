package hse.kpo.studying;

/**
 * Интерфейс с метадом Car takeCar
 */
public interface ICarProvider {
    /**
     * Метод получения по покупателю подходящего для него, имеющегося автомобиля
     * @param customer - покупатель
     * @return -  optional на Car (можем не подобрать машину
     */
    Car takeCar(Customer customer); // Метод возвращает optional на Car, что означает, что метод может ничего не вернуть
}
