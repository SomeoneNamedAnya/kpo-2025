package homework;

import homework.application.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class MainTest {

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
    void contextTest() {
        Assertions.assertNotNull(animalRegistrationService);
        Assertions.assertNotNull(animalTreatService);
        Assertions.assertNotNull(animalTransferService);
        Assertions.assertNotNull(enclosureCleaningService);
        Assertions.assertNotNull(enclosureRegistrationService);
        Assertions.assertNotNull(feedingOrganizationService);
        Assertions.assertNotNull(zooStatisticsService);

    }


}