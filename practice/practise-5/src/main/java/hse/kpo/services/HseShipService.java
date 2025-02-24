package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.ICarProvider;
import hse.kpo.interfaces.ICustomerProvider;
import hse.kpo.interfaces.IShipProvider;
import hse.kpo.observer.Sales;
import hse.kpo.observer.SalesObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class HseShipService {
    private final IShipProvider shipProvider;
    final List<SalesObserver> observers = new ArrayList<>();
    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }
    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    private final ICustomerProvider customerProvider;
    @Sales
    public void sellShip()
    {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getShip()))
                .forEach(customer -> {
                    var ship = shipProvider.takeShip(customer);
                    if (Objects.nonNull(ship)) {
                        notifyObserversForSale(customer, ProductionTypes.CATAMARAN, ship.getHIN());
                        customer.setShip(ship);
                    } else {
                        log.warn("No ship in ShipService");
                    }
                });
    }
}
