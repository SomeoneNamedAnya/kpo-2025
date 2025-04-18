package homework.domain.events;

import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.value_objects.Time;
import lombok.Getter;

@Getter
public class CleaningEvent implements IEvent{
    private final Enclosure enclosure;
    private final Time time;
    public String getType() {
        return "CleaningEvent";
    }
    public CleaningEvent(Enclosure enclosure, Time time) {
        this.enclosure = enclosure;
        this.time = time;
    }
}
