package homework;

import homework.application.*;
import homework.domain.entities.Animal;
import homework.domain.value_objects.Species;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class AnimalTreatServiceTest {
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
    @DisplayName("Тест treat")
    @Order(1)
    void treatTest() {
        Animal animal1 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name1", 2024, 1, 25, true, "food", false);
        Animal animal2 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.BIRD, "name2", 2015, 7, 27, true, "food", false);

        Assertions.assertTrue(animalTreatService.treatById(1));
        Assertions.assertFalse(animalTreatService.treatById(4));
        Assertions.assertTrue(animal1.isHealth());
        Assertions.assertFalse(animal2.isHealth());
    }

}
