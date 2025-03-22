package org.example.importer;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.factories.BankAccountFactory;
import org.example.factories.CategoryFactory;
import org.example.factories.OperationFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractImporter {
    public void importBankAccount(BankAccountFactory bankAccountFactory,
                                  ArrayList<BankAccount> bankAccounts,
                                  String filename) {
        ArrayList<Map<String, String>> date = readFile(filename);
        if (date == null) return;
        for (Map<String, String> item : date) {


            bankAccounts.add(bankAccountFactory.create(Integer.parseInt(item.get("id")),
                    item.get("name"),
                    Float.parseFloat(item.get("balance"))));
        }
    }

    public void importCategory(CategoryFactory categoryFactory,
                               ArrayList<Category> categories,
                               String filename) {
        ArrayList<Map<String, String>> date = readFile(filename);
        if (date == null) return;
        for (Map<String, String> item : date) {
            categories.add(categoryFactory.create(Integer.parseInt(item.get("id")), item.get("type"), item.get("name")));
        }
    }

    public void importOperation(OperationFactory operationFactory,
                               ArrayList<Operation> operations,
                               String filename) {
        ArrayList<Map<String, String>> date = readFile(filename);
        if (date == null) return;
        for (Map<String, String> item : date) {
            operations.add(operationFactory.create(Integer.parseInt(item.get("id")),
                    item.get("type"), Integer.parseInt(item.get("bankAccountId")),
                    Float.parseFloat(item.get("amount")), LocalDateTime.parse(item.get("date")),
                    Integer.parseInt(item.get("categoryId")), item.get("description")));
        }
    }


    public abstract ArrayList<Map<String, String>> readFile(String filename);

}
