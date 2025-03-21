package org.example.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Category {

    private final int id;
    @Setter
    private String type;
    @Setter
    private String name;

    public Category(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
    public void print() {
        System.out.printf("id = %d; type = %s; name = %s\n",
                id, type, name);
    }
}
