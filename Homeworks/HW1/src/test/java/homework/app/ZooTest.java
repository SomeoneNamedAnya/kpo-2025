package homework.app;
import homework.app.Animal.Animal;
import homework.app.Animal.AnimalExample.Monkey;
import homework.app.Animal.AnimalExample.Tiger;
import homework.app.Vet.Vet;
import homework.app.Zoo.Zoo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ZooTest {
    @Autowired
    private Zoo zoo;

    @Autowired
    private Vet vet;


    @Mock
    Animal animal_second;


    @Test
    @DisplayName("Тест загрузки контекста")
    void contextLoadsTest() {
        Assertions.assertNotNull(zoo);
        Assertions.assertNotNull(vet);
    }
    @Test
    @DisplayName("Корректно ли добавляются животные в зоопарк")
    void addAnimalTest() {
        Animal animal_first = spy(new Monkey("A", 1, 1, 1));
        Animal animal_third = spy(new Tiger("A", 1, 1));
        when(animal_first.getHealth()).thenReturn(10);
        when(animal_second.getHealth()).thenReturn(9);
        when(animal_third.getHealth()).thenReturn(10);

        zoo.addAnimal(animal_first);
        zoo.addAnimal(animal_second);
        zoo.addAnimal(animal_third);
        Assertions.assertEquals(zoo.getListAnimals().size(), 2);
        Assertions.assertEquals(zoo.getListAnimals().get(0), animal_first);
        Assertions.assertEquals(zoo.getListAnimals().get(1), animal_third);
    }
    @Test
    @DisplayName("Корректно ли присваиваются зоопарким инвинтаризационные номера")
    void invNumberTest() {


    }
}
