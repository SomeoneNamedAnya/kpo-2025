package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.domains.HandEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HandEngineTest {
    @Test
    @DisplayName("Тест корректности создания PedalEngine")
    void HandEngineCreateTest() {
        HandEngine handEngine = new HandEngine();
        assertEquals("HandEngine()", handEngine.toString());
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (false)")
    void isCompatibleFalseTest() {
        HandEngine handEngine = new HandEngine();
        Customer person = new Customer("1", 1, 1, 1);
        assertFalse(handEngine.isCompatible(person));
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (true)")
    void isCompatibleTrueTest() {
        HandEngine handEngine = new HandEngine();
        Customer person = new Customer("1", 10, 10, 301);
        assertTrue(handEngine.isCompatible(person));
    }

}
