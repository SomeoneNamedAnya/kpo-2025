package hse.kpo.factories;
import hse.kpo.domains.Car;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.ICarFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

/**
 * Класс реализующий интерфейс ICarFactory, описывает фабрику создания автомобилей ручного привода
 */
@Component
public class HandCarFactory implements ICarFactory<EmptyEngineParams> {
    /**
     * Метод для создания объектов машин ручного привода
     * @param carParams - параметры двигателя для машины
     * @param carNumber - номер машины
     * @return - ссылка на созданную машину
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
