package org.example.factories;

import org.example.domains.Operation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OperationFactory {
    private int id = 0;
    public Operation create(String type, int bankAccountId,
                            float amount, LocalDateTime date, int categoryId,
                            String description) {

        if (!type.equals("expense") && !type.equals("income")) {
            return null;
        }
        id += 1;
        return new Operation(id, type, bankAccountId, amount, date, categoryId, description);
    }
    public Operation create(int selfId, String type, int bankAccountId,
                     float amount, LocalDateTime date, int categoryId,
                     String description) {
        if (!type.equals("expense") && !type.equals("income")) {
            return null;
        }
        id += 1;
        if (selfId  < id) {
            id -= 1;
            return null;
        }

        id = selfId;
        return new Operation(id, type, bankAccountId, amount, date, categoryId, description);
    }


}
