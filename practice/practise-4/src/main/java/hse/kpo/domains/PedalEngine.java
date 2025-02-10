package hse.kpo.domains;

import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;
/**
 * Класс реализующий интерфейс IEngine, описывает двигатели педальных автомобилей
 */
@ToString
@Getter
public class PedalEngine implements IEngine {
    private final int size;
    /**
     * Метод определяющий подходит ли данному покупателю данный двигатель
     * @param customer - покупатель, с которым мы сравниваем двигатель
     * @return true - подходит (LegPower > 5) иначе false
     */
    @Override
    public boolean isCompatible(Customer customer) {
        return customer.getLegPower() > 5;
    }

    /**
     * Конструктор класса
     * @param size - размер двигателя
     */
    public PedalEngine(int size) {
        this.size = size;
    }
}
