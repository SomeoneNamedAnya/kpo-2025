package hse.kpo.studying;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Клас покупателей
 */
@Getter
@ToString
public class Customer {
    private final String name;

    private final int legPower;

    private final int handPower;

    private final int iq;

    @Setter
    private Car car;

    /**
     * Конструктор класса
     * @param name - имя покупателя
     * @param legPower - сила ноги
     * @param handPower - сила руки
     * @param iq - iq
     */
    public Customer(String name, int legPower, int handPower, int iq) {
        this.name = name;
        this.legPower = legPower;
        this.handPower = handPower;
        this.iq = iq;
    }
}
