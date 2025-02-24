package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.ICustomerProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class CustomerStorage implements ICustomerProvider {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer)
    {
        customers.add(customer); // просто добавляем покупателя в список
        log.info("Покупатель {} добавлен", customer);
    }
}
