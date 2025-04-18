package homework.presentation.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record EnclosureRequest(
        @Schema(description = "Вид проживающих животных", example = "PREDATOR")
        @Pattern(regexp = "PREDATOR|HERBIVORE|FISH|BIRD|INSECT",
                 message = "Допустимые значения: PREDATOR, HERBIVORE, FISH, BIRD, INSECT")
        String species,

        @Schema(description = "Размер вольера (метры квадратные)", example = "100")
        @Min(value = 1, message = "Минимальный размер вольера - 1 метр квадратный")
        int size,

        @Schema(description = "Максимальное количество животных в вольере", example = "5")
        @Min(value = 1, message = "Минимальный максимальное кольчество животных в вольере - 1")
        int maxAnimals
) {}

