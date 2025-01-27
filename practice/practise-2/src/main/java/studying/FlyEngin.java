package studying;

import lombok.ToString;

/**
 * Класс реализующий интерфейс IEngine, описывает двигатели для летающих машин
 */
@ToString
public class FlyEngin implements IEngine {
    /**
     * Метод определяющий подходит ли данному покупателю данный двигатель
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true - подходит (iq > 300) иначе false
     */
    public boolean isCompatible(Customer customer) {
        return customer.getIq() > 300;
    }
}
