package homework.app;

import homework.app.Animal.Herbo;
import homework.app.Animal.Monkey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonkeyTest {

    @Test
    @DisplayName("Тест корректности создания объекта")
    void isContactFalseTest() {
        Herbo angryAnimal = new Monkey("Abc", 10, 2, 3);
        Assertions.assertEquals("Abc", angryAnimal.getName());
        Assertions.assertEquals(10, angryAnimal.getAge());
        Assertions.assertEquals(2, angryAnimal.getFood());
        Assertions.assertEquals(3, angryAnimal.getKindness());
    }


}
