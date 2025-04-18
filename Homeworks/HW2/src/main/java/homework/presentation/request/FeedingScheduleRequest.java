package homework.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

public record FeedingScheduleRequest (
    @Schema(description = "Id животного для кого составляется расписание", example = "1")
    @Min(value = 1, message = "Минимальный значение - 1")
    int id,
    @Schema(description = "Время в формате hh/mm/ss", example = "20/44/47")
    String timeToFeed
) {}
