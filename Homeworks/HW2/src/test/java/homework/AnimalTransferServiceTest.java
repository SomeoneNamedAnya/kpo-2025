package homework;

import homework.application.*;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
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
public class AnimalTransferServiceTest {
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
    @DisplayName("Тест placed")
    @Order(1)
    void placedTest() {
        Animal animal1 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name1", 2005, 11, 23, true, "food", true);
        Animal animal2 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name2", 2005, 3, 11, true, "food", true);

        enclosureRegistrationService.createEnclosure(Species.PREDATOR, 100, 2);
        enclosureRegistrationService.createEnclosure(Species.BIRD, 50, 2);
        enclosureRegistrationService.createEnclosure(Species.HERBIVORE, 1000, 2);

        Assertions.assertFalse(animalTransferService.placed(1, 2));
        Assertions.assertFalse(animalTransferService.placed(-1, 2));
        Assertions.assertFalse(animalTransferService.placed(1, -2));
        Assertions.assertFalse(animalTransferService.placed(1, 3));
        Assertions.assertTrue(animalTransferService.placed(1, 1));
        Assertions.assertTrue(animalTransferService.placed(2, 1));

        Assertions.assertEquals(animal1, enclosureRegistrationService.getAnimals(1).get(0));
        Assertions.assertEquals(animal2, enclosureRegistrationService.getAnimals(1).get(1));
    }

    @Test
    @DisplayName("Тест moveOut")
    @Order(2)
    void moveOutTest() {
        Assertions.assertTrue(animalTransferService.moveOut(1));
        Assertions.assertFalse(animalTransferService.moveOut(-1));
        Assertions.assertEquals(1, enclosureRegistrationService.getAnimals(1).size());
    }
}
