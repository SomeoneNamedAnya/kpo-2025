package hse.kpo.factories.ships;

import hse.kpo.domains.LevitationEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IShipFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

@Component
public class LevitationShipFactory implements IShipFactory<EmptyEngineParams> {
    @Override
    public Ship create(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new LevitationEngine();
        return new Ship(shipNumber, engine);
    }

}
