package org.homework.application.factory;

import org.aspectj.weaver.ast.Or;
import org.homework.adapters.dto.request.OrderRequest;
import org.homework.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public Order create(int userId, float cost, String description) {
        return new Order(userId, cost, description, "NEW");
    }
}
