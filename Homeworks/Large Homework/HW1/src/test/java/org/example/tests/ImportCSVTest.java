package org.example.tests;

import org.example.date_base.Cache;
import org.example.importer.CsvImporter;
import org.example.services.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ImportCSVTest {

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

    }
    @Test
    @DisplayName("Тест saveAll")
    @Order(2)
    void saveAll() {
        Assertions.assertDoesNotThrow(() -> categoryManager.importDate(new CsvImporter(), "category.csv"));
        Assertions.assertDoesNotThrow(() -> operationManager.importDate(new CsvImporter(), "operation.csv"));
        Assertions.assertDoesNotThrow(() -> bankAccountManager.importDate(new CsvImporter(), "bankaccount.csv"));

        Assertions.assertEquals(4, categoryManager.getAllCategory().size());
        Assertions.assertEquals(6, operationManager.getAllOperation().size());
        Assertions.assertEquals(3, bankAccountManager.getAllBankAccounts().size());

    }
}
