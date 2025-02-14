package homework.app;

import homework.app.Animal.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PredatorTest {
    @Test
    @DisplayName("Тест функции isContact() для хищьника")
    void isContactFalseTest() {
        Predator angryAnimal = new Wolf("1", 1, 1);
        Predator kindAnimal = new Tiger("1", 1, 1);
        Assertions.assertFalse(angryAnimal.isContact());
        Assertions.assertFalse(kindAnimal.isContact());
    }

}
