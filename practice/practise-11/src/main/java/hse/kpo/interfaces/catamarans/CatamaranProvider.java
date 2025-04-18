package hse.kpo.interfaces.catamarans;

import hse.kpo.domains.Catamaran;
import hse.kpo.domains.Customer;
import hse.kpo.domains.cars.Car;

public interface CatamaranProvider {

    /**
     * Метод покупки машины.
     *
     * @param customer - покупатель
     * @return - {@link Car}
     */
    Catamaran takeCatamaran(Customer customer);
}
