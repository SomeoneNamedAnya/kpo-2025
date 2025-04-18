package homework;

import homework.application.*;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.entities.FeedingSchedule;
import homework.domain.events.FeedingTimeEvent;
import homework.domain.value_objects.Species;
import homework.domain.value_objects.Time;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ZooStatisticServiceTest {
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
    @DisplayName("Тест makeNewTime")
    @Order(1)
    void makeNewTimeTest() {
        Animal animal1 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name1", 2005, 11, 23, true, "food", true);
        Animal animal2 = animalRegistrationService.createAnimal("SomeAnimal",
                Species.PREDATOR, "name2", 2005, 3, 11, true, "food", true);

        Enclosure enclosure1 = enclosureRegistrationService.createEnclosure(Species.PREDATOR, 100, 2);
        Enclosure enclosure2 = enclosureRegistrationService.createEnclosure(Species.BIRD, 50, 2);
        Enclosure enclosure3 = enclosureRegistrationService.createEnclosure(Species.HERBIVORE, 1000, 2);


        FeedingSchedule fs1 = feedingOrganizationService.makeNewTime(1, new Time(21, 0,0));
        feedingOrganizationService.makeNewTime(1, new Time(22, 10,40));
        feedingOrganizationService.makeNewTime(1, new Time(22, 11,40));
        feedingOrganizationService.makeNewTime(1, new Time(22, 11,39));
        FeedingSchedule fs2 = feedingOrganizationService.makeNewTime(2, new Time(11, 50,2));
        feedingOrganizationService.makeNewTime(2, new Time(23, 7,4));
        feedingOrganizationService.makeNewTime(2, new Time(1, 22,1));
        feedingOrganizationService.makeNewTime(2, new Time(6, 17,6));

        Assertions.assertEquals(4, zooStatisticsService.getAnimalFeedingSchedule(1).size());
        Assertions.assertNull(zooStatisticsService.getAnimalFeedingSchedule(19));

    }

    @Test
    @DisplayName("Тест count")
    @Order(2)
    void countTest() {
        Assertions.assertEquals(2, zooStatisticsService.getCntOfAllAnimal());
        Assertions.assertEquals(3, zooStatisticsService.getCntOfAllEnclosure());
        Assertions.assertEquals(8, zooStatisticsService.getCntOfAllFeedingSchedule());
    }

    @Test
    @DisplayName("Тест getAll")
    @Order(3)
    void getAllTest() {
        Assertions.assertEquals(2, zooStatisticsService.getAllAnimal().size());
        Assertions.assertEquals(3, zooStatisticsService.getAllEnclosure().size());
        Assertions.assertEquals(8, zooStatisticsService.getAllFeedingSchedule().size());
    }
    @Test
    @DisplayName("Тест getHistory")
    @Order(4)
    void getHistoryTest() {
        enclosureCleaningService.cleanEnclosureById(1);
        enclosureCleaningService.cleanEnclosureById(2);
        enclosureCleaningService.cleanEnclosureById(3);
        animalTreatService.treatById(1);

        Assertions.assertEquals(4, zooStatisticsService.getHistoryOfZooEvent().size());
    }

    @Test
    @DisplayName("Тест getHistoryFeed")
    @Order(5)
    void getHistoryFeedTest() {
        feedingOrganizationService.feedAll();
        Assertions.assertEquals(8, zooStatisticsService.getHistoryOfFeed().size());
    }

    @Test
    @DisplayName("Тест getFilterEnclosure")
    @Order(6)
    void getFilterEnclosureTest() {
        animalTransferService.placed(1, 1);
        Assertions.assertEquals(1, zooStatisticsService.getFilterEnclosure(Species.PREDATOR, 1).size());
        Assertions.assertEquals(0, zooStatisticsService.getFilterEnclosure(Species.PREDATOR, 2).size());
        animalTransferService.placed(2, 1);
        Assertions.assertEquals(0, zooStatisticsService.getFilterEnclosure(Species.PREDATOR, 1).size());
    }

    @Test
    @DisplayName("Тест findById")
    @Order(7)
    void findByIdTest() {
        Assertions.assertEquals(1, zooStatisticsService.getAnimal(1).get().getId());
        Assertions.assertEquals(1, zooStatisticsService.getEnclosure(1).get().getId());
        Assertions.assertEquals(1, zooStatisticsService.getFeedingSchedule(1).get().getId());
        Assertions.assertTrue(zooStatisticsService.getAnimal(17).isEmpty());
        Assertions.assertTrue(zooStatisticsService.getEnclosure(17).isEmpty());
        Assertions.assertTrue(zooStatisticsService.getFeedingSchedule(17).isEmpty());
    }

    @Test
    @DisplayName("Тест findEnclosureByAnimalId")
    @Order(8)
    void findEnclosureByAnimalIdTest() {
        animalTransferService.moveOut(2);
        Assertions.assertTrue(zooStatisticsService.findEnclosureByAnimalId(2).isEmpty());
        Assertions.assertEquals(1, zooStatisticsService.findEnclosureByAnimalId(1).get().getId());
    }


}
