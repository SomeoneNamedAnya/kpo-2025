package org.homework.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public record OrderCompletedEvent(
    int orderId,
    String updatedParameter
) {}
