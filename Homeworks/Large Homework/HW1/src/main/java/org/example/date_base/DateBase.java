package org.example.date_base;

import org.example.domains.BankAccount;
import org.example.domains.Category;
import org.example.domains.Operation;
import org.example.factories.BankAccountFactory;
import org.example.factories.CategoryFactory;
import org.example.factories.OperationFactory;
import org.example.interfaces.IConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
@Component
public class DateBase implements IConnection {

    @Autowired
    BankAccountFactory bankAccountFactory;

    @Autowired
    CategoryFactory categoryFactory;

    @Autowired
    OperationFactory operationFactory;

    Connection connection = null;

    String getBankAccountsQuery = "select * from BankAccounts";
    String getCategoriesQuery = "select * from Categories";
    String getOperations = "select * from Operations";

    Statement statementBankAccount = null;
    Statement statementCategory = null;
    Statement statementOperation = null;
    ResultSet resBankAccounts = null;
    ResultSet resCategories = null;
    ResultSet resOperations = null;

    public boolean connectToDatabase(String filename) {
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(filename))){
            properties.load(in);
        } catch (IOException e) {
            return false;
        }

        try {
            this.connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
            System.out.println("Подключено к базе данных");
            statementBankAccount = this.connection.createStatement();
            statementCategory = this.connection.createStatement();
            statementOperation = this.connection.createStatement();

            resBankAccounts = statementBankAccount.executeQuery(getBankAccountsQuery);
            resCategories = statementCategory.executeQuery(getCategoriesQuery);
            resOperations = statementOperation.executeQuery(getOperations);

        } catch (SQLException e) {
            return false;
        }

        return true;
    }
    @Override
    public ArrayList<Category> getCategory() {
        ArrayList<Category> category = new ArrayList<>();
        try {
            while (resCategories.next()) {
                Category temp = categoryFactory.create(resCategories.getInt("id"),
                        resCategories.getString("type"),
                        resCategories.getString("name")
                        );
                if (temp == null) {
                    throw new SQLException("Incorrect order of id");
                }
                category.add(temp);
            }

            return category;
        } catch (SQLException e) {
            return category;
        }
    }
    @Override
    public ArrayList<BankAccount> getBankAccount() {
        ArrayList<BankAccount> bankAccount = new ArrayList<>();
        try {
            while (resBankAccounts.next()) {
                BankAccount temp = bankAccountFactory.create(resBankAccounts.getInt("id"),
                        resBankAccounts.getString("name"),
                        resBankAccounts.getFloat("balance"));
                if (temp == null) {
                    throw new SQLException("Incorrect order of id");
                }

                bankAccount.add(temp);
            }

            return bankAccount;
        } catch (SQLException e) {
            return bankAccount;
        }

    }
    @Override
    public ArrayList<Operation> getOperation() {
        ArrayList<Operation> operation = new ArrayList<>();
        try {
            while (resOperations.next()) {
                Operation temp = operationFactory.create(resOperations.getInt("id"),
                        resOperations.getString("type"),
                        resOperations.getInt("bank_account_id"),
                        resOperations.getFloat("amount"),
                        resOperations.getTimestamp("date").toLocalDateTime(),
                        resOperations.getInt("category_id"),
                        resOperations.getString("description"));
                if (temp == null) {
                    throw new SQLException("Incorrect order of id");
                }
                operation.add(temp);
            }
            return operation;
        } catch (SQLException e) {
            return operation;
        }
    }

    @Override
    public int insertBankAccount(BankAccount bankAccount) {
        return -1;
    }

    @Override
    public int insertCategory(Category category) {
        return -1;
    }

    @Override
    public int insertOperation(Operation operation) {
        return -1;
    }

    @Override
    public boolean saveAllCategories() {
        return true;
    }

    @Override
    public boolean saveAllOperations() {
        return true;
    }

    @Override
    public boolean saveAllBankAccounts() {
        return true;
    }

    public boolean saveAllCategories(ArrayList<Category> allCategories) {
        try {
            Statement statementDrop = connection.createStatement();
            statementDrop.executeUpdate("delete from categories");

            for (Category x : allCategories) {
                try {
                    PreparedStatement statement = connection.prepareStatement("insert into categories (id, name, type) values (?, ?, ?)");
                    statement.setInt(1, x.getId());
                    statement.setString(2, x.getName());
                    statement.setString(3, x.getType());
                    int rows = statement.executeUpdate();
                    if (rows != 1) {
                        throw new Exception("Данные не добавились в базу данных");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean saveAllOperations(ArrayList<Operation> allOperation) {
        try {
            Statement statement_drop = connection.createStatement();
            statement_drop.executeUpdate("delete from operations");
            for (Operation x : allOperation) {
                try {

                    PreparedStatement statement = connection
                            .prepareStatement("insert into operations (id, type, bank_account_id, category_id, amount, date, description) values (?, ?, ?, ?, ?, ?, ?)");
                    statement.setInt(1, x.getId());
                    statement.setString(2, x.getType());
                    statement.setInt(3, x.getBankAccountId());
                    statement.setInt(4, x.getCategoryId());
                    statement.setFloat(5, x.getAmount());
                    statement.setTimestamp(6, Timestamp.valueOf(x.getDate()));
                    statement.setString(7, x.getDescription());
                    int rows = statement.executeUpdate();
                    if (rows != 1) {
                        throw new Exception("Данные не добавились в базу данных");
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                    throw new RuntimeException(e);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public boolean saveAllBankAccounts(ArrayList<BankAccount> allBankAccounts) {
        try {
            Statement statement_drop = connection.createStatement();
            statement_drop.executeUpdate("delete from BankAccounts");
            System.out.println(allBankAccounts);
            for (BankAccount x : allBankAccounts) {
                //System.out.println(x);
                try {
                    //System.out.println(x);
                    PreparedStatement statement = connection
                            .prepareStatement("insert into BankAccounts (id, name, balance) values (?, ?, ?)");
                    statement.setInt(1, x.getId());
                    statement.setString(2, x.getName());
                    statement.setFloat(3, x.getBalance());
                    int rows = statement.executeUpdate();
                    if (rows != 1) {
                        throw new Exception("Данные не добавились в базу данных");
                    }
                    System.out.println(x);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

}
