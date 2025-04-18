package homework.domain.value_objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AnimalType {
    private final String title;
    private final Species species;
    public AnimalType(String animalType, Species species) {
        this.title = animalType;
        this.species = species;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AnimalType that = (AnimalType) obj;
        return title.equals(that.title) && species.equals(that.species);
    }
}
