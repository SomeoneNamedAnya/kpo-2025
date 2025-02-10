package hse.kpo;

import hse.kpo.domains.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarTest {
    @Test
    @DisplayName("Тест корректности создания машины с FlyEngine")
    void CarFlyTest() {
        FlyEngin flyEngin = new FlyEngin();
        Car car = new Car(1, flyEngin);
        assertEquals(1, car.getVIN());
    }

    @Test
    @DisplayName("Тест корректности создания машины с HandEngine")
    void CarHandTest() {
        HandEngine handEngine = new HandEngine();
        Car car = new Car(2, handEngine);
        assertEquals(2, car.getVIN());
    }
    @Test
    @DisplayName("Тест корректности создания машины с PedalEngine")
    void CarPedalTest() {
        PedalEngine pedalEngine = new PedalEngine(30);
        Car car = new Car(3, pedalEngine);
        assertEquals(3, car.getVIN());
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible")
    void isCompatibleTest() {
        FlyEngin flyEngin = spy(new FlyEngin());
        Customer person = spy(new Customer("1", 1, 1, 1));
        Car car = new Car(1, flyEngin);

        when(flyEngin.isCompatible(person)).thenReturn(true);
        assertTrue(car.isCompatible(person));
    }
}
