package org.example.factories;

import org.example.domains.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory{
    private int id = 0;
    public Category create(String type, String name) {

        if (!type.equals("expense") && !type.equals("income")) {
            return null;
        }
        id += 1;
        return new Category(id, type, name);
    }

    public Category create(int selfId, String type, String name) {
        if (!type.equals("expense") && !type.equals("income")) {
            return null;
        }
        id += 1;
        if (selfId < id) {
            id -= 1;
            return null;
        }

        id = selfId;
        return new Category(id, type, name);
    }

}
