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
public class EnclosureRegistrationServiceTest {
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
        Enclosure enclosure1 = enclosureRegistrationService.createEnclosure(Species.PREDATOR, 100, 2);
        Enclosure enclosure2 = enclosureRegistrationService.createEnclosure(Species.BIRD, 50, 2);
        Enclosure enclosure3 = enclosureRegistrationService.createEnclosure(Species.HERBIVORE, 1000, 2);

        Assertions.assertEquals(1, enclosure1.getId());
        Assertions.assertEquals(100, enclosure1.getSize());
        Assertions.assertEquals(0, enclosure1.getCurAnimals().size());
        Assertions.assertEquals(2, enclosure1.getMaxAnimals());
        Assertions.assertEquals(Species.PREDATOR, enclosure1.getSpecies());

        Assertions.assertEquals(2, enclosure2.getId());
        Assertions.assertEquals(50, enclosure2.getSize());
        Assertions.assertEquals(0, enclosure2.getCurAnimals().size());
        Assertions.assertEquals(2, enclosure2.getMaxAnimals());
        Assertions.assertEquals(Species.BIRD, enclosure2.getSpecies());

        Assertions.assertEquals(3, enclosure3.getId());
        Assertions.assertEquals(1000, enclosure3.getSize());
        Assertions.assertEquals(0, enclosure3.getCurAnimals().size());
        Assertions.assertEquals(2, enclosure3.getMaxAnimals());
        Assertions.assertEquals(Species.HERBIVORE, enclosure3.getSpecies());

    }

    @Test
    @DisplayName("Тест delete")
    @Order(2)
    void deleteTest() {
        Assertions.assertTrue(enclosureRegistrationService.deleteEnclosure(3));
        Assertions.assertFalse(enclosureRegistrationService.deleteEnclosure(3));
    }

    @Test
    @DisplayName("Тест getAnimals")
    @Order(3)
    void getAnimalsTest() {
        Animal animal1 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name1", 2005, 11, 23, true, "food", true);
        Animal animal2 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name2", 2005, 3, 11, true, "food", true);

        animalTransferService.placed(1, 1);
        animalTransferService.placed(2, 1);

        Assertions.assertEquals(2, enclosureRegistrationService.getAnimals(1).size());
        Assertions.assertEquals(animal1, enclosureRegistrationService.getAnimals(1).get(0));
        Assertions.assertEquals(animal2, enclosureRegistrationService.getAnimals(1).get(1));

    }
}
