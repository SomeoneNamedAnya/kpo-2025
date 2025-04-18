package homework.domain.entities;

import homework.domain.events.AnimalMovedInEvent;
import homework.domain.events.AnimalMovedOutEvent;
import homework.domain.events.CleaningEvent;
import homework.domain.value_objects.Species;
import homework.domain.value_objects.Time;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Enclosure {
    private int id;
    private Species species;
    private int size;
    private List<Animal> curAnimals = new ArrayList<>();
    private int maxAnimals;

    public Enclosure(int id, Species species, int size, int maxAnimals) {
        this.id = id;
        this.species = species;
        this.size = size;
        this.maxAnimals = maxAnimals;
    }

    public AnimalMovedOutEvent delAnimal(Animal animal) {
        curAnimals.remove(animal);
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return new AnimalMovedOutEvent(animal, this,  new Time(hour, minute, second));
    }

    public AnimalMovedInEvent pushAnimal(Animal animal) {
        curAnimals.add(animal);
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return new AnimalMovedInEvent(animal, this,  new Time(hour, minute, second));
    }

    public boolean hasAnimal(Animal animal) {
        return curAnimals.contains(animal);
    }

    public CleaningEvent clean() {
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        return new CleaningEvent(this, new Time(hour, minute, second));
    }

    public boolean isAcceptable(Animal animal) {
        if (maxAnimals == curAnimals.size()) {
            return false;
        }
        return animal.getAnimalType().getSpecies() == species;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Enclosure that = (Enclosure) obj;
        return that.id == id;
    }
}
