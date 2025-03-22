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
public class AnalyticTest {
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
    @DisplayName("Тест getDifference")
    void getDifferenceTest() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Assertions.assertEquals(9250, analytics.getDifference(1, LocalDateTime.parse("2025-03-17 09:10:36", format), LocalDateTime.parse("2025-03-17 09:10:36", format)));

    }

    @Test
    @DisplayName("Тест groupByExpense")
    void groupByExpenseTest() {


        Assertions.assertEquals(1, analytics.groupByExpense(1).size());
        Assertions.assertEquals(2, analytics.groupByExpense(1).get("third").size());

    }

    @Test
    @DisplayName("Тест groupByIncome")
    void groupByIncomeTest() {

        Assertions.assertEquals(2, analytics.groupByIncome(1).size());
        Assertions.assertEquals(2, analytics.groupByIncome(1).get("first").size());
        Assertions.assertEquals(1, analytics.groupByIncome(1).get("second").size());

    }

    @Test
    @DisplayName("Тест topIncome")
    void topIncomeTest() {


        Assertions.assertEquals(2, analytics.topIncome(1).size());
        Assertions.assertEquals(10550, analytics.topIncome(1).get(0).getFirstValue());
        Assertions.assertEquals("first", analytics.topIncome(1).get(0).getSecondValue());
        Assertions.assertEquals(300, analytics.topIncome(1).get(1).getFirstValue());
        Assertions.assertEquals("second", analytics.topIncome(1).get(1).getSecondValue());

    }

    @Test
    @DisplayName("Тест topExpense")
    void topExpenseTest() {

        Assertions.assertEquals(1, analytics.topExpense(1).size());
        Assertions.assertEquals(1600, analytics.topExpense(1).get(0).getFirstValue());
        Assertions.assertEquals("third", analytics.topExpense(1).get(0).getSecondValue());
    }



}
