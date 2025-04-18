package homework.domain.entities;

import homework.domain.events.FeedingTimeEvent;
import homework.domain.value_objects.Food;
import homework.domain.value_objects.Time;
import lombok.Getter;

import java.util.Optional;

@Getter
public class FeedingSchedule {
    private int id;
    private Animal animal;
    private Time time;
    private Food food;
    private boolean isSkip = false;


    public FeedingSchedule(int id, Animal animal, Time time, Food food) {
        this.id = id;
        this.animal = animal;
        this.time = time;
        this.food = food;
    }

    public boolean doSkip() {
        isSkip = true;
        return true;
    }

    public boolean undoSkip() {
        isSkip = false;
        return true;
    }

    public Optional<FeedingTimeEvent> make() {
        if (isSkip) {
            isSkip = false;
            return Optional.empty();
        }
        return Optional.of(animal.feed(time));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FeedingSchedule that = (FeedingSchedule) obj;
        return that.id == id;
    }


}
