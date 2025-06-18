package org.homework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int userId;
    float amount;
    String description;
    String status;

    public Order(int userId, float amount, String description, String status) {
        this.userId = userId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }
}
