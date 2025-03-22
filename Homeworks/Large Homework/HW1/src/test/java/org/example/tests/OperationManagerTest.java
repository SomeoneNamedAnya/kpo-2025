package org.example.tests;

import org.example.date_base.Cache;
import org.example.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class OperationManagerTest {

    @Autowired
    private BankAccountManager bankAccountManager;
    @Autowired
    private OperationManager operationManager;
    @Autowired
    private CategoryManager categoryManager;
    @Autowired
    private Analytics analytics;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private Cache cache;
    @Test
    @DisplayName("Тест загрузки контекста")
    @Order(1)
    void contextLoadsTest() {
        Assertions.assertNotNull(bankAccountManager);
        Assertions.assertNotNull(operationManager);
        Assertions.assertNotNull(categoryManager);
        Assertions.assertNotNull(analytics);
        Assertions.assertNotNull(balanceService);
        Assertions.assertNotNull(cache);

        bankAccountManager.setConnection(cache);
        operationManager.setConnection(cache);
        categoryManager.setConnection(cache);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        bankAccountManager.makeNewBankAccount("test", 0);
        categoryManager.makeNewCategory("first", "income");
        categoryManager.makeNewCategory("second", "income");
        categoryManager.makeNewCategory("third", "expense");
        operationManager.makeNewOperation("income", 1, 10500, LocalDateTime.parse("2025-03-17 09:10:36", format), 1, "no");
        operationManager.makeNewOperation("income", 1, 300, LocalDateTime.parse("2025-03-17 09:10:36", format), 2, "no");
        operationManager.makeNewOperation("expense", 1, 800, LocalDateTime.parse("2025-03-17 09:10:36", format), 3, "no");
        operationManager.makeNewOperation("expense", 1, 800, LocalDateTime.parse("2025-03-17 09:10:36", format), 3, "no");
        operationManager.makeNewOperation("income", 1, 50, LocalDateTime.parse("2025-03-17 09:10:36", format), 1, "no");


    }
    @Test
    @DisplayName("Тест getAllOperation")
    @Order(2)
    void getAllOperationTest() {
        Assertions.assertEquals(5, operationManager.getAllOperation().size());
    }
    @Test
    @DisplayName("Тест deleteOperation")
    @Order(3)
    void deleteOperationTest() {
        operationManager.deleteOperation(1);
        Assertions.assertEquals(4, operationManager.getAllOperation().size());
        Assertions.assertEquals(2, operationManager.getAllOperation().get(0).getId());
        Assertions.assertEquals(3, operationManager.getAllOperation().get(1).getId());
        Assertions.assertEquals(4, operationManager.getAllOperation().get(2).getId());
        Assertions.assertEquals(5, operationManager.getAllOperation().get(3).getId());

    }

    @Test
    @DisplayName("Тест refactorOperation")
    @Order(4)
    void refactorOperationTest() {
        operationManager.refactorOperation(2, "new description");
        Assertions.assertEquals("new description", operationManager.getOperation(2).getDescription());
    }
}
