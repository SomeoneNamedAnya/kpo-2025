package org.homework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "transactional_info")
public class TransactionalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int accountId;

    float sum;

    String type;

    private LocalDateTime time;

    public TransactionalInformation(int accountId, float sum, String type, LocalDateTime time) {
        this.accountId = accountId;
        this.sum = sum;
        this.type = type;
        this.time = time;
    }

}
