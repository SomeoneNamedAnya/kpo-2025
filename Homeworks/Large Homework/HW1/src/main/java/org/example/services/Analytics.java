package org.example.services;

import org.example.auxiliary_tools.Pair;
import org.example.domains.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Analytics {
    @Autowired
    BankAccountManager bankAccountManager;
    @Autowired
    OperationManager operationManager;
    @Autowired
    CategoryManager categoryManager;

    public float getDifference(int id, LocalDateTime start, LocalDateTime end) {

        var ref = new Object() {
            float income = 0;
            float expense = 0;
        };

        operationManager.getAllOperation().forEach(x -> {
            if (x.getBankAccountId() == id && x.getType().equals("income")) {
                if (x.getDate().isAfter(start) && x.getDate().isBefore(end) ||
                    x.getDate().equals(start) || x.getDate().equals(end)) {
                    ref.income += x.getAmount();
                }

            }
        });

        operationManager.getAllOperation().forEach(x -> {
            if (x.getBankAccountId() == id && x.getType().equals("expense")) {
                if (x.getDate().isAfter(start) && x.getDate().isBefore(end) ||
                        x.getDate().equals(start) || x.getDate().equals(end)) {
                    ref.expense += x.getAmount();
                }

            }
        });

        return ref.income - ref.expense;

    }

    public Map<String, ArrayList<Operation>> groupByIncome(int id) {
        Map<String, ArrayList<Operation>> typeOfOperation = new HashMap<>();
        Map<Integer, String> idToCategory = new HashMap<>();

        categoryManager.getAllCategory().forEach(x -> {
            if (x.getType().equals("income")) {
                typeOfOperation.put(x.getName(), new ArrayList<>());
                idToCategory.put(x.getId(), x.getName());
            }
        });

        operationManager.getAllOperation().forEach(x -> {
            if (x.getBankAccountId() == id && x.getType().equals("income")) {
                typeOfOperation.get(idToCategory.get(x.getCategoryId())).add(x);
            }
        });

        return typeOfOperation;
    }

    public Map<String, ArrayList<Operation>> groupByExpense(int id) {
        Map<String, ArrayList<Operation>> typeOfOperation = new HashMap<>();
        Map<Integer, String> idToCategory = new HashMap<>();

        categoryManager.getAllCategory().forEach(x -> {
            if (x.getType().equals("expense")) {
                typeOfOperation.put(x.getName(), new ArrayList<>());
                idToCategory.put(x.getId(), x.getName());
            }
        });

        operationManager.getAllOperation().forEach(x -> {
            if (x.getBankAccountId() == id && x.getType().equals("expense")) {
                typeOfOperation.get(idToCategory.get(x.getCategoryId())).add(x);
            }
        });

        return typeOfOperation;
    }

    public ArrayList<Pair> topExpense(int id) {
        ArrayList<Pair> top = new ArrayList<>();
        Map<String, ArrayList<Operation>> groups = groupByExpense(id);
        Map<String, Float> amount = new HashMap<>();

        categoryManager.getAllCategory().forEach(x -> {
            if (x.getType().equals("expense")) {
                amount.put(x.getName(), (float) 0);
            }
        });

        groups.forEach((x, y) -> {
            y.forEach(k -> amount.put(x, amount.get(x) + k.getAmount()));
        });

        amount.forEach((x, y) -> top.add(new Pair(y, x)));

        top.sort(Pair::compare);

        return top;
    }

    public ArrayList<Pair> topIncome(int id) {
        ArrayList<Pair> top = new ArrayList<>();
        Map<String, ArrayList<Operation>> groups = groupByIncome(id);
        Map<String, Float> amount = new HashMap<>();

        categoryManager.getAllCategory().forEach(x -> {
            if (x.getType().equals("income")) {
                amount.put(x.getName(), (float) 0);
            }
        });

        groups.forEach((x, y) -> {
            y.forEach(k -> amount.put(x, amount.get(x) + k.getAmount()));
        });

        amount.forEach((x, y) -> top.add(new Pair(y, x)));

        top.sort(Pair::compare);

        return top;
    }

}
