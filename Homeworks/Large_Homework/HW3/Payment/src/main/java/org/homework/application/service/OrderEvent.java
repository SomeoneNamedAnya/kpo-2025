package org.homework.application.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public record OrderEvent(
        int id,
        int userId,
        float amount,
        String description,
        String status
){}
