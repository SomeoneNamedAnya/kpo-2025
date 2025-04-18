package hse.kpo.services.catamarans;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;
import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.CustomerProvider;
import hse.kpo.interfaces.cars.CarProvider;
import hse.kpo.interfaces.catamarans.CatamaranFactory;
import hse.kpo.interfaces.catamarans.CatamaranProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import hse.kpo.observers.Sales;
import hse.kpo.observers.SalesObserver;
import hse.kpo.storages.CatamaranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Сервис продажи катамаранов.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class HseCatamaranService implements CatamaranProvider {

    private final List<SalesObserver> observers = new ArrayList<>();

    private final CustomerProvider customerProvider;
    private final CatamaranRepository catamaranRepository;

    public void addObserver(SalesObserver observer) {
        observers.add(observer);
    }

    private void notifyObserversForSale(Customer customer, ProductionTypes productType, int vin) {
        observers.forEach(obs -> obs.onSale(customer, productType, vin));
    }

    /**
     * Метод продажи машин
     */
    @Sales
    public void sellCatamarans() {
        var customers = customerProvider.getCustomers();
        customers.stream().filter(customer -> Objects.isNull(customer.getCatamaran()))
                .forEach(customer -> {
                    var car = this.takeCatamaran(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCatamaran(car);
                        notifyObserversForSale(customer, ProductionTypes.CATAMARAN, car.getVin());
                    } else {
                        log.warn("No catamaran in CarService");
                    }
                });
    }

    @Override
    public Catamaran takeCatamaran(Customer customer) {

        var filteredCars = catamaranRepository.findAll().stream().filter(car -> car.isCompatible(customer)).toList();

        var firstCar = filteredCars.stream().findFirst();

        firstCar.ifPresent(catamaranRepository::delete);

        return firstCar.orElse(null);
    }

    /**
     * Метод добавления {@link Car} в систему.
     *
     * @param carFactory фабрика для создания автомобилей
     * @param carParams параметры для создания автомобиля
     */
    public <T> Catamaran addCatamaran(CatamaranFactory<T> carFactory, T carParams) {
        return catamaranRepository.save(carFactory.create(carParams));
    }

    public Catamaran addExistingCatamaran(Catamaran car) {
        return catamaranRepository.save(car);
    }

    public Optional<Catamaran> findByVin(Integer vin) {
        return catamaranRepository.findById(vin);
    }

    public void deleteByVin(Integer vin) {
        catamaranRepository.deleteById(vin);
    }

    public List<Catamaran> getCatamaransWithFiltration(String engineType, Integer vin) {
        return catamaranRepository.findCatamaransByEngineTypeAndVinGreaterThan(engineType, vin);
    }

    public List<Catamaran> getCatamarans() {
        return catamaranRepository.findAll();
    }
}