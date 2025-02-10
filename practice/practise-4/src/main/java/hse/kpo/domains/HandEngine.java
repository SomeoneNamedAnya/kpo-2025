package hse.kpo.domains;
import hse.kpo.interfaces.IEngine;
import lombok.ToString;

/**
 * Класс реализующий интерфейс IEngine, описывает двигатели для машин ручного привода
 */
@ToString
public class HandEngine implements IEngine {
    /**
     * Метод определяющий подходит ли данному покупателю данный двигатель
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true - подходит (HandPower > 5) иначе false
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getHandPower() > 5;
    }
}
