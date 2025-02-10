package hse.kpo.services;

import hse.kpo.interfaces.ICarProvider;
import hse.kpo.interfaces.ICustomerProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Класс для моделирования покупки (подбору) автомобилей по имеющимся автомобилям и имеющимся покупателям
 */
@Component
public class HseCarService {

    @Autowired
    private final ICarProvider carProvider;

    @Autowired
    private final ICustomerProvider customerProvider;

    /**
     * Конструктор класса
     * @param carProvider - параметор по предоставлению имеющихся автомобилей
     * @param customersProvider - параметр по предоставлению имеющихся покупателей
     */
    @Autowired
    public HseCarService(ICarProvider carProvider, ICustomerProvider customersProvider)
    {
        this.carProvider = carProvider;
        this.customerProvider = customersProvider;
    }

    /**
     * Метод назначения имеющимся покупателям имеющиеся машины
     */
    public void sellCars()
    {
        // получаем список покупателей
        var customers = customerProvider.getCustomers();
        // пробегаемся по полученному списку
        customers.stream().filter(customer -> Objects.isNull(customer.getCar()))
                .forEach(customer -> {
                    var car = carProvider.takeCar(customer);
                    if (Objects.nonNull(car)) {
                        customer.setCar(car);
                    }
                });
    }
}