package hse.kpo.studying;

import org.springframework.stereotype.Component;

/**
 * Класс реализующий интерфейс ICarFactory, описывает фабрику создания летающих автомобилей
 */
@Component
public class FlyCarFactory implements ICarFactory<EmptyEngineParams>{
    /**
     * Метод для создания объектов летающих машин
     * @param carParams - параметры двигателя для машины
     * @param carNumber - номер машины
     * @return - ссылка на созданную машину
     */
    @Override
    public Car createCar(EmptyEngineParams carParams, int carNumber) {
        var engine = new FlyEngin(); // Создаем двигатель без каких-либо параметров

        return new Car(carNumber, engine); // создаем автомобиль с ручным приводом
    }
}
