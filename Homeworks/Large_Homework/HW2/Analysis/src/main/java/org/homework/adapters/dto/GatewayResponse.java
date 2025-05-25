package org.homework.adapters.dto;

public record GatewayResponse (
    boolean isExist,
    int countParagraphs,
    int countWords,
    int countCharacters,
    String location
){}
