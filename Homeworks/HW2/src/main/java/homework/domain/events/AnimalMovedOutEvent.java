package homework.domain.events;

import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.value_objects.Time;
import lombok.Getter;

@Getter
public class AnimalMovedOutEvent implements IEvent{
    private final Animal animal;
    private final Enclosure enclosure;
    private final Time time;

    public String getType() {
        return "AnimalMovedOutEvent";
    }
    public AnimalMovedOutEvent(Animal animal, Enclosure enclosure, Time  time) {
        this.animal = animal;
        this.enclosure = enclosure;
        this.time = time;
    }
}
