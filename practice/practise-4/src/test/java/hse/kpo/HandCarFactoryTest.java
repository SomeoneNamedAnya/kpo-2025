package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.factories.FlyCarFactory;
import hse.kpo.factories.HandCarFactory;
import hse.kpo.factories.PedalCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.params.PedalEngineParams;
import hse.kpo.services.CarService;
import hse.kpo.services.CustomerStorage;
import hse.kpo.services.HseCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class HandCarFactoryTest {
    @Test
    @DisplayName("Тест функции createCar")
    void createCarTest() {
        EmptyEngineParams engineParams = new EmptyEngineParams();
        HandCarFactory handCarFactory = new HandCarFactory();
        Car testCar = handCarFactory.createCar(engineParams, 1);
        Assertions.assertEquals("Car(engine=HandEngine(), VIN=1)", testCar.toString());
    }

}
