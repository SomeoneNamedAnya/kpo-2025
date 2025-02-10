package hse.kpo;
import hse.kpo.domains.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
public class FlyEnginTest {
    @Test
    @DisplayName("Тест корректности создания FlyEngine")
    void FlyEngineCreateTest() {
        FlyEngin flyEngin = new FlyEngin();
        assertEquals("FlyEngin()", flyEngin.toString());
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (должно быть false)")
    void isCompatibleFalseTest() {
        FlyEngin flyEngin = new FlyEngin();
        Customer person = new Customer("1", 1, 1, 1);
        assertFalse(flyEngin.isCompatible(person));
    }

    @Test
    @DisplayName("Тест корректности выполнения функции isCompatible (должно быть true)")
    void isCompatibleTrueTest() {
        FlyEngin flyEngin = new FlyEngin();
        Customer person = new Customer("1", 1, 1, 301);
        assertTrue(flyEngin.isCompatible(person));
    }


}
