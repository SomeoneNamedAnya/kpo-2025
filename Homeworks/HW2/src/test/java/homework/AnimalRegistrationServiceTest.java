package homework;

import homework.application.*;
import homework.domain.entities.Animal;
import homework.domain.value_objects.Date;
import homework.domain.value_objects.Food;
import homework.domain.value_objects.Nickname;
import homework.domain.value_objects.Species;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AnimalRegistrationServiceTest {
    @Autowired
    private AnimalRegistrationService animalRegistrationService;
    @Autowired
    private AnimalTreatService animalTreatService;
    @Autowired
    private AnimalTransferService animalTransferService;
    @Autowired
    private EnclosureCleaningService enclosureCleaningService;
    @Autowired
    private EnclosureRegistrationService enclosureRegistrationService;
    @Autowired
    private FeedingOrganizationService feedingOrganizationService;
    @Autowired
    private ZooStatisticsService zooStatisticsService;


    @Test
    @DisplayName("Тест create")
    @Order(1)
    void createTest() {
        Animal animal1 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name1", 2005, 11, 23, true, "food", true);
        Animal animal2 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.BIRD, "name2", 2005, 3, 11, true, "food", true);

        Assertions.assertEquals("SomeAnimal", animal1.getAnimalType().getTitle());
        Assertions.assertEquals(Species.PREDATOR, animal1.getAnimalType().getSpecies());
        Assertions.assertEquals(1, animal1.getId());
        Assertions.assertEquals(new Food("food"), animal1.getFood());
        Assertions.assertEquals(new Nickname("name1"), animal1.getNickname());
        Assertions.assertEquals(new Date(2005, 11, 23), animal1.getDateOfBirth());
        Assertions.assertTrue(animal1.isSex());
        Assertions.assertTrue(animal1.isHealth());

        Assertions.assertEquals("SomeAnimal", animal2.getAnimalType().getTitle());
        Assertions.assertEquals(Species.BIRD, animal2.getAnimalType().getSpecies());
        Assertions.assertEquals(2, animal2.getId());
        Assertions.assertEquals(new Food("food"), animal2.getFood());
        Assertions.assertEquals(new Nickname("name2"), animal2.getNickname());
        Assertions.assertEquals(new Date(2005, 3, 11), animal2.getDateOfBirth());
        Assertions.assertTrue(animal2.isSex());
        Assertions.assertTrue(animal2.isHealth());

    }

    @Test
    @DisplayName("Тест delete")
    @Order(2)
    void deleteTest() {
        animalRegistrationService.deleteAnimal(1);
        Assertions.assertEquals(1, zooStatisticsService.getAllAnimal().size());
        Animal animal = zooStatisticsService.getAllAnimal().get(0);
        Assertions.assertEquals("SomeAnimal", animal.getAnimalType().getTitle());
        Assertions.assertEquals(Species.BIRD, animal.getAnimalType().getSpecies());
        Assertions.assertEquals(2, animal.getId());
        Assertions.assertEquals(new Food("food"), animal.getFood());
        Assertions.assertEquals(new Nickname("name2"), animal.getNickname());
        Assertions.assertEquals(new Date(2005, 3, 11), animal.getDateOfBirth());
        Assertions.assertTrue(animal.isSex());
        Assertions.assertTrue(animal.isHealth());
        Assertions.assertFalse(animalRegistrationService.deleteAnimal(1));
    }
}
