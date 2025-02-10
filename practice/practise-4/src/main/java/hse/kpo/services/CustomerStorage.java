package hse.kpo.studying;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует интерфейс ICustomerProvider и хранит список покупателей
 */
@Component
public class CustomerStorage implements ICustomerProvider{
    private List<Customer> customers = new ArrayList<>();

    /**
     * Класс возвращает список покупателей
     * @return список List<Customer>
     */
    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Добавление покупателя в список customers
     * @param customer - добавляемый покупатель
     */
    public void addCustomer(Customer customer)
    {
        customers.add(customer); // просто добавляем покупателя в список
    }
}
