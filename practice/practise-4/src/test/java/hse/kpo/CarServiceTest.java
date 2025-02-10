package hse.kpo;

import ch.qos.logback.core.net.server.Client;
import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.FlyEngin;
import hse.kpo.factories.FlyCarFactory;
import hse.kpo.params.EmptyEngineParams;
import hse.kpo.services.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarServiceTest {
    @Mock
    FlyCarFactory flyCarFactory;

    @Mock
    FlyEngin flyEngin;

    @Test
    @DisplayName("Тест присвоения машины Customer из CarService")
    void CustomerCreateTest() {
        EmptyEngineParams engineParams = new EmptyEngineParams();
        Customer customerAccept = new Customer("a", 1, 1, 1);
        Customer customerReject = new Customer("a", 1, 1, 1);

        when(flyEngin.isCompatible(customerAccept)).thenReturn(true);
        when(flyEngin.isCompatible(customerReject)).thenReturn(false);
        Car someCar = new Car(1, flyEngin);

        when(flyCarFactory.createCar(engineParams, 1)).thenReturn(someCar);

        CarService carService = new CarService();
        carService.addCar(flyCarFactory, engineParams);

        assertNull(carService.takeCar(customerReject));
        assertNotNull(carService.takeCar(customerAccept));

    }


}
