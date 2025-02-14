package homework.app;

import homework.app.Animal.Herbo;
import homework.app.Animal.Monkey;
import homework.app.Animal.Rabbit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HerboTest {

    @Test
    @DisplayName("Тест функции isContact() для недружелюбного животного")
    void isContactFalseTest() {
        Herbo angryAnimal = new Rabbit("1", 1, 1, 2);
        Assertions.assertFalse(angryAnimal.isContact());
    }

    @Test
    @DisplayName("Тест функции isContact() для дружелюбного животного")
    void isContactTrueTest() {
        Herbo kindAnimal = new Monkey("1", 1, 1, 9);
        Assertions.assertTrue(kindAnimal.isContact());
    }

}
