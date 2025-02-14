package homework.app;

import homework.app.Animal.Monkey;
import homework.app.Animal.Rabbit;
import homework.app.Animal.Tiger;
import homework.app.Animal.Wolf;
import homework.app.Enum.ZooObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {

    @Test
    @DisplayName("Тест корректности возвращения названия объекта")
    void getTitleTest() {
        ZooObject monkey = ZooObject.MONKEY;
        ZooObject wolf = ZooObject.WOLF;
        ZooObject rabbit = ZooObject.RABBIT;
        ZooObject tiger = ZooObject.TIGER;

        Assertions.assertEquals("Monkey", monkey.getTitle());
        Assertions.assertEquals("Wolf", wolf.getTitle());
        Assertions.assertEquals("Rabbit", rabbit.getTitle());
        Assertions.assertEquals("Tiger", tiger.getTitle());
    }
    @Test
    @DisplayName("Тест корректности разделения на хищьников и травоядных")
    void getIsHerboTest() {
        ZooObject monkey = ZooObject.MONKEY;
        ZooObject wolf = ZooObject.WOLF;
        ZooObject rabbit = ZooObject.RABBIT;
        ZooObject tiger = ZooObject.TIGER;

        Assertions.assertTrue(monkey.getIsHerbo());
        Assertions.assertFalse(wolf.getIsHerbo());
        Assertions.assertTrue(rabbit.getIsHerbo());
        Assertions.assertFalse(tiger.getIsHerbo());
    }

    @Test
    @DisplayName("Тест корректности построения экземпляров эивотных")
    void buildSpecificAnimalTest() {
        ZooObject monkey = ZooObject.MONKEY;
        ZooObject wolf = ZooObject.WOLF;
        ZooObject rabbit = ZooObject.RABBIT;
        ZooObject tiger = ZooObject.TIGER;

        Assertions.assertTrue(monkey.buildSpecificAnimal("1", 1, 1, 1) instanceof Monkey);
        Assertions.assertTrue(wolf.buildSpecificAnimal("1", 1, 1, 1) instanceof Wolf);
        Assertions.assertTrue(rabbit.buildSpecificAnimal("1", 1, 1, 1) instanceof Rabbit);
        Assertions.assertTrue(tiger.buildSpecificAnimal("1", 1, 1, 1) instanceof Tiger);
    }

}
