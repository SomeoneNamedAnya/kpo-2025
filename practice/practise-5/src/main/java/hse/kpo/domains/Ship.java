package hse.kpo.domains;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Ship {
    private IEngine engine;

    @Getter
    private int HIN;

    public Ship(int HIN, IEngine engine) {
        this.HIN = HIN;
        this.engine = engine;
    }

    public boolean isCompatible(Customer customer) {
        return this.engine.isCompatible(customer, ProductionTypes.CATAMARAN);// внутри метода просто вызываем соответствующий метод двигателя
    }
}
