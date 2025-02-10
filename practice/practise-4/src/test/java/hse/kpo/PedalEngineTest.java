package hse.kpo;

import hse.kpo.domains.Customer;
import hse.kpo.domains.PedalEngine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PedalEngineTest {
    @Test
    @DisplayName("Тест корректности создания PedalEngine")
    void PedalEngineCreateTest() {
        PedalEngine pedalEngin = new PedalEngine(10);
        assertEquals("PedalEngine(size=10)", pedalEngin.toString());
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (false)")
    void isCompatibleFalseTest() {
        PedalEngine pedalEngine = new PedalEngine(10);
        Customer person = new Customer("1", 1, 1, 1);
        assertFalse(pedalEngine.isCompatible(person));
    }


    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (true)")
    void isCompatibleTrueTest() {
        PedalEngine pedalEngine = new PedalEngine(10);
        Customer person = new Customer("1", 10, 1, 301);
        assertTrue(pedalEngine.isCompatible(person));
    }
}
