package org.homework.adapters.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;

@Builder
public record OrderRequest (
    @Schema(description = "Id пользователя", example = "1")
    @Min(value = 0, message = "Минимальная id пользователя 0")
    int id,
    @Schema(description = "Стоимость заказа", example = "1000")
    @Min(value = 0, message = "Минимальная стоимость заказа 0 рублей")
    float cost,

    @Schema(description = "Описание заказа", example = "Лапша быстрого приготовления 6шт.")
    String description

)
{}
