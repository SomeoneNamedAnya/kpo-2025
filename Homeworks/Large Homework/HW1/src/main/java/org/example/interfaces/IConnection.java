package org.example.interfaces;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;

import java.util.ArrayList;

public interface IConnection {
    public boolean connectToDatabase(String filename);
    public ArrayList<BankAccount> getBankAccount();
    public ArrayList<Category> getCategory();
    public ArrayList<Operation> getOperation();

    public int insertBankAccount(BankAccount bankAccount);
    public int insertCategory(Category category);
    public int insertOperation(Operation operation);
    public boolean saveAllCategories();
    public boolean saveAllOperations();
    public boolean saveAllBankAccounts();
}
