package hse.kpo;

import hse.kpo.domains.Car;
import hse.kpo.domains.Customer;
import hse.kpo.domains.FlyEngin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class CustomerTest {
    @Test
    @DisplayName("Тест создания экземпляра класса Customer")
    void CustomerCreateTest() {
        Customer person = new Customer("Tina", 6, 6, 350);
        assertEquals("Customer(name=Tina, legPower=6, handPower=6, iq=350, car=null)", person.toString());
    }

    @Test
    @DisplayName("Тест создания экземпляра класса Customer и присвоения машины")
    void CustomerSetCarTest() {
        Customer person = new Customer("Tina", 6, 6, 350);
        person.setCar(new Car(1, new FlyEngin()));
        assertEquals("Customer(name=Tina, legPower=6, handPower=6, iq=350, car=Car(engine=FlyEngin(), VIN=1))", person.toString());
    }

}
