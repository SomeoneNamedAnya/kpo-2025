package org.homework.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


public record OrderCompletedEvent(
    int orderId,
    String updatedParameter
) {}
