package hse.kpo.factories.ships;

import hse.kpo.domains.Car;
import hse.kpo.domains.PedalEngine;
import hse.kpo.domains.Ship;
import hse.kpo.interfaces.IShipFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import org.springframework.stereotype.Component;

@Component
public class PedalShipFactory implements IShipFactory<PedalEngineParams> {
    @Override
    public Ship create(PedalEngineParams shipParams, int shipNumber) {
        var engine = new PedalEngine(shipParams.pedalSize()); // создаем двигатель на основе переданных параметров

        return new Ship(shipNumber, engine);
    }

}
