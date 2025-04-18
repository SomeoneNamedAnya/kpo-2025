package homework.domain.entities;
import homework.domain.events.AnimalTreatEvent;
import homework.domain.events.FeedingTimeEvent;
import homework.domain.value_objects.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Animal {
    private int id;
    private AnimalType animalType;
    private Nickname nickname;
    private Date dateOfBirth;
    // 0 - male, 1 - female
    private boolean sex;
    private Food food;
    // 0 - sick, 1 - healthy
    private boolean health;


    public Animal(int id, AnimalType animalType, Nickname nickname,
                  Date dateOfBirth, boolean sex, Food food,
                  boolean health) {
        this.id = id;
        this.animalType = animalType;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.food = food;
        this.health = health;
    }

    public FeedingTimeEvent feed(Time curTime) {
        return new FeedingTimeEvent(this, curTime);
    }

    public AnimalTreatEvent treat() {
        health = true;
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return new AnimalTreatEvent(this, new Time(hour, minute, second));
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal that = (Animal) obj;
        return that.id == id;
    }
}
