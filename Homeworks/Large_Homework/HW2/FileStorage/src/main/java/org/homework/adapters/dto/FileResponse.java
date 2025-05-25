package org.homework.adapters.dto;

public record FileResponse(
        boolean isExist,
        String filename,
        String file

) {}
