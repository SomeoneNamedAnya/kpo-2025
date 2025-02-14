package homework.app;

import homework.app.Animal.Predator;
import homework.app.Animal.Wolf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WolfTest {

    @Test
    @DisplayName("Тест корректности создания объекта")
    void isContactFalseTest() {
        Predator angryAnimal = new Wolf("Abc", 10, 20);
        Assertions.assertEquals("Abc", angryAnimal.getName());
        Assertions.assertEquals(10, angryAnimal.getAge());
        Assertions.assertEquals(20, angryAnimal.getFood());
    }


}
