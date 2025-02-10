package hse.kpo.studying;

import lombok.Getter;
import lombok.ToString;

/**
 * Класс машин с полями:
 * IEngine engine - двигатель
 * int VIN - номер автомобиля
 */
@ToString
public class Car {

    private IEngine engine;

    @Getter
    private int VIN;

    /**
     * Конструктор класса Car
     * @param VIN - номер автомобиля
     * @param engine - двигатель
     */
    public Car(int VIN, IEngine engine) {
        this.VIN = VIN;
        this.engine = engine;
    }

    /**
     * Метод для проверки подходит ли заданный покупатель для данной машины
     * @param customer - покупатель
     * @return true если подходит, false иначе
     */
    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer); // внутри метода просто вызываем соответствующий метод двигателя
    }
}
