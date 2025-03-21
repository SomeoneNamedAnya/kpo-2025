package org.example.date_base;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.interfaces.IConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class Cache implements IConnection {
    @Autowired
    private DateBase dateBaseConnection;

    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    ArrayList<Operation> operations = new ArrayList<>();


    public boolean connectToDatabase(String filename) {

        boolean ans = dateBaseConnection.connectToDatabase(filename);
        if (ans) {
            categories = dateBaseConnection.getCategory();
            bankAccounts = dateBaseConnection.getBankAccount();
            operations = dateBaseConnection.getOperation();
        }
        return ans;
    }

    @Override
    public ArrayList<BankAccount> getBankAccount() {
        return bankAccounts;
    }

    @Override
    public ArrayList<Category> getCategory() {
        return categories;
    }

    @Override
    public ArrayList<Operation> getOperation() {
        return operations;
    }

    @Override
    public int insertBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
        return bankAccount.getId();
    }

    @Override
    public int insertCategory(Category category) {
        this.categories.add(category);
        return category.getId();
    }

    @Override
    public int insertOperation(Operation operation) {
        this.operations.add(operation);
        return operation.getId();
    }

    @Override
    public boolean saveAllCategories() {
        dateBaseConnection.saveAllCategories(categories);
        return true;
    }
    @Override
    public boolean saveAllOperations() {
        dateBaseConnection.saveAllOperations(operations);
        return true;
    }
    @Override
    public boolean saveAllBankAccounts() {
        dateBaseConnection.saveAllBankAccounts(bankAccounts);
        return true;
    }

}
