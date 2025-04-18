package homework;

import homework.application.*;
import homework.domain.entities.Animal;
import homework.domain.entities.FeedingSchedule;
import homework.domain.value_objects.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ValueObjectTest {
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
    @DisplayName("Тест Date")
    @Order(1)
    void DateTest() {
        Date data1 = new Date(1000, 9, 18);
        Date data2 = new Date(1000, 9, 18);

        Date data3 = new Date(1000, 9, 12);
        Date data4 = new Date(1000, 10, 12);


        Assertions.assertEquals(data2, data1);
        Assertions.assertNotEquals(data2, data3);
        Assertions.assertEquals(data3, data3);
        Assertions.assertNotEquals(data2, new Date(1000, 10, 12));

        Assertions.assertEquals(1, data2.compareTo(data3));
        Assertions.assertEquals(-1, data3.compareTo(data4));
        Assertions.assertEquals(0, data1.compareTo(data2));

    }

    @Test
    @DisplayName("Тест Time")
    @Order(2)
    void TimeTest() {
        Time time1 = new Time(10, 12, 14);
        Time time2 = new Time(10, 12, 14);
        Time time3 = new Time(11, 12, 14);
        Time time4 = new Time(10, 14, 14);
        Time time5 = new Time(10, 14, 16);
        Assertions.assertEquals(time1, time2);
        Assertions.assertEquals(time1, time1);
        Assertions.assertNotEquals(time4, time5);
        Assertions.assertEquals(-1, time2.compareTo(time3));
        Assertions.assertEquals(1, time3.compareTo(time4));
        Assertions.assertEquals(1, time5.compareTo(time4));
        Assertions.assertEquals(0, time1.compareTo(time2));

    }

    @Test
    @DisplayName("Тест AnimalType")
    @Order(2)
    void AnimalTypeTest() {
        AnimalType animal1 = new AnimalType("animal1", Species.BIRD);
        AnimalType animal2 = new AnimalType("animal2", Species.BIRD);
        AnimalType animal3 = new AnimalType("animal2", Species.HERBIVORE);
        AnimalType animal4 = new AnimalType("animal2", Species.HERBIVORE);

        Assertions.assertEquals(animal3, animal4);
        Assertions.assertEquals(animal3, animal3);
        Assertions.assertNotEquals(animal1, animal2);

    }



}
