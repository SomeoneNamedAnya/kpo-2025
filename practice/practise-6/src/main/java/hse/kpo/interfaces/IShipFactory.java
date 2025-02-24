package hse.kpo.interfaces;

import hse.kpo.domains.Ship;

public interface IShipFactory<TParams> {
    Ship create(TParams shipParams, int shipNumber);
}
