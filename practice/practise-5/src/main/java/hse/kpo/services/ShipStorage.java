package hse.kpo.services;

import hse.kpo.domains.Customer;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IShipFactory;
import hse.kpo.interfaces.IShipProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class ShipStorage implements IShipProvider {

    private final List<Ship> ships = new ArrayList<>();

    private int shipNumberCounter = 0;

    @Override
    public Ship takeShip(Customer customer) {

        var filteredShips = ships.stream().filter(ship -> ship.isCompatible(customer)).toList();

        var firstShip = filteredShips.stream().findFirst();

        firstShip.ifPresent(ships::remove);

        return firstShip.orElse(null);
    }

    public <TParams> void addShip(IShipFactory<TParams> shipFactory, TParams shipParams)
    {
        // создаем автомобиль из переданной фабрики
        var ship = shipFactory.create(
                shipParams, // передаем параметры
                ++shipNumberCounter // передаем номер - номер будет начинаться с 1
        );

        ships.add(ship); // добавляем автомобиль
        log.info("Катамаран с номером {} добавлен", shipNumberCounter);
    }
}
