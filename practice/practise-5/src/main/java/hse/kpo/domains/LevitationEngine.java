package hse.kpo.domains;

import hse.kpo.enums.ProductionTypes;
import hse.kpo.interfaces.IEngine;
import lombok.ToString;

/**
 * Класс, реализующий {@link IEngine} ручного типа.
 */
@ToString
public class LevitationEngine implements IEngine {
    @Override
    public boolean isCompatible(Customer customer, ProductionTypes type) {
        if(type == null) throw new RuntimeException("This type of production doesn't exist");
        return switch (type) {
            case CAR -> customer.getIq() > 300;
            case CATAMARAN -> customer.getIq() >= 200;
            default -> throw new RuntimeException("This type of production doesn't exist");
        };
    }
}
