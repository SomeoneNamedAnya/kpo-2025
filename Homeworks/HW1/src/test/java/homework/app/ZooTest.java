package homework.app;
import homework.app.Animal.Animal;
import homework.app.Animal.Monkey;
import homework.app.Animal.Rabbit;
import homework.app.Animal.Tiger;
import homework.app.Animal.Wolf;
import homework.app.Vet.Vet;
import homework.app.Zoo.Zoo;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZooTest {
    @Autowired
    private Zoo zoo;

    @Autowired
    private Vet vet;
    @Mock
    Animal animalSecond;
    @Test
    @DisplayName("Тест загрузки контекста")
    @Order(1)
    void contextLoadsTest() {
        Assertions.assertNotNull(zoo);
        Assertions.assertNotNull(vet);
    }
    @Test
    @DisplayName("Корректно ли добавляются животные в зоопарк")
    @Order(2)
    void addAnimalTest() {
        Animal animalFirst = spy(new Monkey("A", 1, 1, 10));
        Animal animalThird = spy(new Tiger("A", 1, 1));
        Animal animalFourth = spy(new Rabbit("A", 1, 1, 10));
        Animal animalFifth = spy(new Wolf("A", 1, 1));
        when(animalFirst.getHealth()).thenReturn(10);
        when(animalSecond.getHealth()).thenReturn(9);
        when(animalThird.getHealth()).thenReturn(10);
        when(animalFourth.getHealth()).thenReturn(10);
        when(animalFifth.getHealth()).thenReturn(10);

        zoo.addAnimal(animalFirst);
        zoo.addAnimal(animalSecond);
        zoo.addAnimal(animalThird);
        zoo.addAnimal(animalFourth);
        zoo.addAnimal(animalFifth);
        Assertions.assertEquals(4, zoo.getListAnimals().size());
        Assertions.assertEquals(animalFirst, zoo.getListAnimals().get(0));
        Assertions.assertEquals(animalThird, zoo.getListAnimals().get(1));
    }
    @Test
    @DisplayName("Корректно ли присваиваются зоопарким инвинтаризационные номера")
    @Order(3)
    void invNumberTest() {
        Assertions.assertEquals(1, zoo.getListAnimals().get(0).getInvNumber());
        Assertions.assertEquals(2, zoo.getListAnimals().get(1).getInvNumber());
    }

    @Test
    @Order(4)
    @DisplayName("Тест что функция makeAnimalFoodReport() не кидает неожиданные исключения")
    void makeAnimalFoodReportTest() {
        Assertions.assertDoesNotThrow(() -> zoo.makeAnimalFoodReport());
    }

    @Test
    @Order(5)
    @DisplayName("Тест что функция makeContactAnimalReport() не кидает неожиданные исключения")
    void makeContactAnimalReportTest() {
        Assertions.assertDoesNotThrow(() -> zoo.makeContactAnimalReport());
    }

    @Test
    @Order(6)
    @DisplayName("Тест что функция makeGeneralReport() не кидает неожиданные исключения")
    void makeGeneralReportTest() {
        Assertions.assertDoesNotThrow(() -> zoo.makeGeneralReport());
    }
}
