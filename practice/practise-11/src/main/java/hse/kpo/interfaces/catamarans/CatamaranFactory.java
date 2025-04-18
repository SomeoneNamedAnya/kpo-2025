package hse.kpo.interfaces.catamarans;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.cars.Car;

/**
 * Интерфейс для определения методов фабрик.
 *
 * @param <T> параметры для фабрик
 */
public interface CatamaranFactory<T> {

    Catamaran create(T catamaranParams);
}
