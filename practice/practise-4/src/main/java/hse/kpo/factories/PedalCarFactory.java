package hse.kpo.studying;

import org.springframework.stereotype.Component;

/**
 * Класс реализующий интерфейс ICarFactory, описывает фабрику создания педальных автомобилей
 */
@Component
public class PedalCarFactory implements ICarFactory<PedalEngineParams>{
    /**
     * Метод для создания объектов педальных автомобилей
     * @param carParams - параметры двигателя для машины
     * @param carNumber - номер машины
     * @return - ссылка на созданную машину
     */
    @Override
    public Car createCar(PedalEngineParams carParams, int carNumber) {
        var engine = new PedalEngine(carParams.pedalSize()); // создаем двигатель на основе переданных параметров

        return new Car(carNumber, engine); // возвращаем собранный автомобиль с установленным двигателем и определенным номером
    }
}
