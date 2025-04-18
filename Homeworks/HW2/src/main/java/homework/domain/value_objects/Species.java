package homework.domain.value_objects;

import java.util.Arrays;
import java.util.Optional;

public enum Species {

    HERBIVORE("HERBIVORE"),
    PREDATOR("PREDATOR"),
    FISH("FISH"),
    BIRD("BIRD"),
    INSECT("INSECT");

    private final String name;
    Species(String name) {
        this.name = name;
    }

    public static Optional<Species> find(String name) {
        return Arrays.stream(values()).filter(type -> type.name.equals(name)).findFirst();
    }


}
