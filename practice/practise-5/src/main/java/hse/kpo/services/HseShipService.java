package hse.kpo.services;

import hse.kpo.interfaces.ICarProvider;
import hse.kpo.interfaces.ICustomerProvider;
import hse.kpo.interfaces.IShipProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class HseShipService {
    private final IShipProvider shipProvider;

    private final ICustomerProvider customerProvider;

    public void sellShip()
    {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getShip()))
                .forEach(customer -> {
                    var ship = shipProvider.takeShip(customer);
                    if (Objects.nonNull(ship)) {
                        customer.setShip(ship);
                    } else {
                        log.warn("No ship in ShipService");
                    }
                });
    }
}
