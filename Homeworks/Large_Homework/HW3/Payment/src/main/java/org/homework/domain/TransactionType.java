package org.homework.domain;

import java.util.Arrays;
import java.util.Optional;

public enum TransactionType {
    TOP_UP("TOP_UP"),
    WITHDRAW("WITHDRAW");
    private final String name;

    TransactionType(String name) {
        this.name = name;
    }
    public static Optional<TransactionType> find(String name) {
        return Arrays.stream(values()).filter(type -> type.name.equals(name)).findFirst();
    }
}
