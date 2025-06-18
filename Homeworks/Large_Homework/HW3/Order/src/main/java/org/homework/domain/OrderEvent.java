package org.homework.domain;

public record OrderEvent (
        int id,
        int userId,
        float amount,
        String description,
        String status
){}
