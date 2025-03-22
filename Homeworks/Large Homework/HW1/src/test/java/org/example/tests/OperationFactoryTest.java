package org.example.tests;

import org.example.date_base.Cache;
import org.example.factories.BankAccountFactory;
import org.example.factories.CategoryFactory;
import org.example.factories.OperationFactory;
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
public class OperationFactoryTest {
    @Autowired
    private OperationFactory operationFactory;

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
    @Autowired
    private BankAccountFactory bankAccountFactory;
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
        categoryManager.deleteCategory(1);
        categoryManager.refactorCategory(2, "newnewCategory2");
        bankAccountManager.makeNewBankAccount("test2", 0);
        bankAccountManager.deleteBankAccounts(1);
        bankAccountManager.refactorBankAccounts(2, "newTest2");
        operationManager.deleteOperation(1);
        operationManager.refactorOperation(2, "new description");
    }
    @Test
    @DisplayName("Тест create")
    @Order(1)
    void getAllCategoryTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Assertions.assertNull(operationFactory.create(1,"income", 1, 10500, LocalDateTime.parse("2025-03-17 09:10:36", format), 1, "no"));
        Assertions.assertNull(operationFactory.create(6, "1234", 1, 10500, LocalDateTime.parse("2025-03-17 09:10:36", format), 1, "no"));
        Assertions.assertEquals(6, operationFactory.create(6,"income", 1, 10500, LocalDateTime.parse("2025-03-17 09:10:36", format), 1, "no").getId());
    }
}
