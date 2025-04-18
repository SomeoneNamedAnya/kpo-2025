package homework.presentation.request;

import homework.domain.value_objects.AnimalType;
import homework.domain.value_objects.Date;
import homework.domain.value_objects.Food;
import homework.domain.value_objects.Nickname;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
@Builder
public record AnimalRequest(
        @Schema(description = "Название животного", example = "Cat")
        String animalType,

        @Schema(description = "Вид животного", example = "PREDATOR")
        @Pattern(regexp = "PREDATOR|HERBIVORE|FISH|BIRD|INSECT",
                 message = "Допустимые значения: PREDATOR, HERBIVORE, FISH, BIRD, INSECT")
        String species,

        @Schema(description = "Имя", example = "Fluffy")
        String nickname,

        @Schema(description = "Дата рождения в формате dd/mm/yyyy", example = "23/11/2005")
        String dateOfBirth,

        @Schema(description = "Пол (false - Male, true - Female)")
       //@Pattern(regexp = "false|1", message = "Допустимые значения: 0, 1")
        boolean sex,

        @Schema(description = "Название еды, которую ест животное")
        String food,

        @Schema(description = "Состояние здоровья (false - Sick, true - Healthy)")
        //@Pattern(regexp = "0|1", message = "Допустимые значения: 0, 1")
        boolean health
) {}

