package hse.kpo.domains;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.IEngine;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс, реализующий {@link IEngine} педального типа.
 */
@ToString
@Getter
public class PedalEngine implements IEngine {
    private final int size;

    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        if(type == null) throw new RuntimeException("This type of production doesn't exist");
        return switch (type) {
            case CAR -> customer.getLegPower() > 5;
            case CATAMARAN -> customer.getLegPower() >= 2;
            default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }

    public PedalEngine(int size) {
        this.size = size;
    }
}
