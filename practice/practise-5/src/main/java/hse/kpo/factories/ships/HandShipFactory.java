package hse.kpo.factories.ships;

import hse.kpo.domains.HandEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IShipFactory;
import hse.kpo.params.EmptyEngineParams;
import org.springframework.stereotype.Component;

@Component
public class HandShipFactory implements IShipFactory<EmptyEngineParams> {
    @Override
    public Ship create(EmptyEngineParams shipParams, int shipNumber) {
        var engine = new HandEngine();
        return new Ship(shipNumber, engine);
    }

}
