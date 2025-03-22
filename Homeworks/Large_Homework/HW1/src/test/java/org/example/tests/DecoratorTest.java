package org.example.tests;

import org.example.commands.Decorator;
import org.example.commands.print_all.PrintAccountCommand;
import org.example.commands.print_all.PrintCategoryCommand;
import org.example.commands.print_all.PrintOperationCommand;
import org.example.date_base.Cache;
import org.example.factories.BankAccountFactory;
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
public class DecoratorTest {
    @Autowired
    OperationManager operationManager;
    @Autowired
    CategoryManager categoryManager;
    @Autowired
    BankAccountManager bankAccountManager;
    @Autowired
    private OperationFactory operationFactory;

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
    @DisplayName("Тест decorator")
    @Order(2)
    void getAllOperationTest() {
        Decorator dec1 = new Decorator(new PrintOperationCommand(operationManager));
        Decorator dec2 = new Decorator(new PrintCategoryCommand(categoryManager));
        Decorator dec3 = new Decorator(new PrintAccountCommand(bankAccountManager));

        Assertions.assertDoesNotThrow(dec1::execute);
        Assertions.assertDoesNotThrow(dec2::execute);
        Assertions.assertDoesNotThrow(dec3::execute);

    }

}
