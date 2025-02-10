package hse.kpo.studying;

/**
 * Интерфейс с метадом создания автомобилей createCar
 * @param <TParams> - шаблон для параметров двигателя для создания автомобилей
 */
public interface ICarFactory<TParams> {
    /**
     * Метод для создания объектов машин
     * @param carParams - параметры двигателя для машины
     * @param carNumber - номер машины
     */
    Car createCar(TParams carParams, int carNumber);
}
