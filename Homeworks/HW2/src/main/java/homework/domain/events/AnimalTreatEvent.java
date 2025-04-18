package homework.domain.events;

import homework.domain.entities.Animal;
import homework.domain.value_objects.Time;
import lombok.Getter;

@Getter
public class AnimalTreatEvent implements IEvent{
    private final Animal animal;

    private final Time time;
    public String getType() {
        return "AnimalTreatEvent";
    }
    public AnimalTreatEvent(Animal animal, Time time) {
        this.animal = animal;
        this.time = time;
    }
}
