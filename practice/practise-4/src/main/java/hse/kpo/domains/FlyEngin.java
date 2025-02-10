package hse.kpo.studying;

import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Класс реализующий интерфейс IEngine, описывает двигатели для летающих машин
 */

@ToString
@Component
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
