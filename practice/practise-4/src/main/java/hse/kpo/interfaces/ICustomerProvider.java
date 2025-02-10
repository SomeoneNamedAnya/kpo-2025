package hse.kpo.studying;

import java.util.List;
/**
 * Интерфейс с метадом List<Customer> getCustomers()
 */
public interface ICustomerProvider {
    /**
     * Метод возвращающий список покупателей
     * @return - List<Customer>
     */
    List<Customer> getCustomers(); // метод возвращает коллекцию только для чтения, так как мы не хотим давать вызывающему коду возможность изменять список
}
