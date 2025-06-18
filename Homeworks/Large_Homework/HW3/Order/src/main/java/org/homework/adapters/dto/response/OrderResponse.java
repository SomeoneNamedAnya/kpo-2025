package org.homework.adapters.dto.response;

public record OrderResponse(
        int id,
        int userId,
        float cost,
        String description,
        String status
)
{}
