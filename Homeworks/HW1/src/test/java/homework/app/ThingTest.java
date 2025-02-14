package homework.app;

import homework.app.Inventory.Computer;
import homework.app.Inventory.Table;
import homework.app.Inventory.Thing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThingTest {
    @Test
    @DisplayName("Тест printInfo для инвентаря")
    void printInfoTest() {
        Thing computer = new Computer(1);
        Thing table = new Table(2);
        Assertions.assertDoesNotThrow(() -> computer.printInfo(1));
        Assertions.assertDoesNotThrow(() -> table.printInfo(1));
    }

}
