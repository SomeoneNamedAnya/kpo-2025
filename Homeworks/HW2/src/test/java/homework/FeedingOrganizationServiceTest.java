package homework;

import homework.application.*;
import homework.domain.entities.Animal;
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
public class FeedingOrganizationServiceTest {
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

        FeedingSchedule fs1 = feedingOrganizationService.makeNewTime(1, new Time(21, 0,0));
        feedingOrganizationService.makeNewTime(1, new Time(22, 10,40));
        feedingOrganizationService.makeNewTime(1, new Time(22, 11,40));
        feedingOrganizationService.makeNewTime(1, new Time(22, 11,39));
        FeedingSchedule fs2 = feedingOrganizationService.makeNewTime(2, new Time(11, 50,2));
        feedingOrganizationService.makeNewTime(2, new Time(23, 7,4));
        feedingOrganizationService.makeNewTime(2, new Time(1, 22,1));
        feedingOrganizationService.makeNewTime(2, new Time(6, 17,6));

        Assertions.assertNotEquals(fs1, fs2);
        Assertions.assertEquals(fs1, fs1);


        Assertions.assertEquals(animal1, fs1.getAnimal());
        Assertions.assertEquals(21, fs1.getTime().getHour());
        Assertions.assertEquals(0, fs1.getTime().getMinute());
        Assertions.assertEquals(0, fs1.getTime().getSecond());
        Assertions.assertEquals(animal2, fs2.getAnimal());
        Assertions.assertEquals(11, fs2.getTime().getHour());
        Assertions.assertEquals(50, fs2.getTime().getMinute());
        Assertions.assertEquals(2, fs2.getTime().getSecond());
    }

    @Test
    @DisplayName("Тест doSkip")
    @Order(2)
    void doSkipTest() {
        Assertions.assertTrue(feedingOrganizationService.doSkip(2));
        Assertions.assertTrue(feedingOrganizationService.doSkip(4));
        Assertions.assertFalse(feedingOrganizationService.doSkip(12));
    }

    @Test
    @DisplayName("Тест undoSkip")
    @Order(3)
    void undoSkipTest() {
        Assertions.assertTrue(feedingOrganizationService.undoSkip(4));
        Assertions.assertFalse(feedingOrganizationService.undoSkip(12));

    }

    @Test
    @DisplayName("Тест feedAll")
    @Order(4)
    void feedAllTest() {
        Assertions.assertTrue(feedingOrganizationService.feedAll());

        Assertions.assertEquals(7, zooStatisticsService.getHistoryOfFeed().size());
        Assertions.assertEquals(1, ((FeedingTimeEvent)zooStatisticsService.getHistoryOfFeed().get(0)).getTime().getHour());
    }

    @Test
    @DisplayName("Тест doAllSkipAll")
    @Order(5)
    void doAllSkipTest() {
        Assertions.assertTrue(feedingOrganizationService.doAllSkip());
        Assertions.assertTrue(feedingOrganizationService.feedAll());
        Assertions.assertEquals(7, zooStatisticsService.getHistoryOfFeed().size());
    }

    @Test
    @DisplayName("Тест undoAllSkipAll")
    @Order(6)
    void undoAllSkipTest() {
        Assertions.assertTrue(feedingOrganizationService.doAllSkip());
        Assertions.assertTrue(feedingOrganizationService.undoAllSkip());
        Assertions.assertTrue(feedingOrganizationService.feedAll());
        Assertions.assertEquals(15, zooStatisticsService.getHistoryOfFeed().size());
    }

    @Test
    @DisplayName("Тест doAllSkipAllForAnimal")
    @Order(7)
    void doAllSkipAllForAnimalTest() {
        Assertions.assertTrue(feedingOrganizationService.doAllSkipForAnimal(2));
        Assertions.assertFalse(feedingOrganizationService.doAllSkipForAnimal(5));
        Assertions.assertTrue(feedingOrganizationService.feedAll());
        Assertions.assertEquals(19, zooStatisticsService.getHistoryOfFeed().size());
    }

    @Test
    @DisplayName("Тест undoAllSkipAllForAnimal")
    @Order(8)
    void undoAllSkipAllForAnimalTest() {
        Assertions.assertTrue(feedingOrganizationService.doAllSkipForAnimal(2));
        Assertions.assertFalse(feedingOrganizationService.undoAllSkipForAnimal(10));
        Assertions.assertTrue(feedingOrganizationService.undoAllSkipForAnimal(2));
        Assertions.assertTrue(feedingOrganizationService.feedAll());
        Assertions.assertEquals(27, zooStatisticsService.getHistoryOfFeed().size());
    }

    @Test
    @DisplayName("Тест deleteById")
    @Order(9)
    void deleteByIdTest() {
        Assertions.assertTrue(feedingOrganizationService.deleteById(1));
        Assertions.assertFalse(feedingOrganizationService.deleteById(1));
        Assertions.assertEquals(7, zooStatisticsService.getAllFeedingSchedule().size());
    }
}
