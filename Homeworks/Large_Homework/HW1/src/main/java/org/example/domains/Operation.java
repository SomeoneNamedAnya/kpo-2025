package org.example.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Getter
public class Operation {

    private final int id;
    private final String type;
    private final int bankAccountId;
    private final float amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final LocalDateTime date;
    @Setter
    private String description;
    private final int categoryId;

    public Operation(int id, String type, int bankAccountId,
                     float amount, LocalDateTime date, int categoryId,
                     String description) {
        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.description = description;

    }
    public void print() {
        System.out.printf("id = %d; type = %s; bankAccountId = %d; amount = %f; date = %s; categoryId = %d; description = %s\n",
                id, type, bankAccountId, amount, date.toString(), categoryId, description);
    }

}
