package homework.domain.value_objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Time implements Comparable<Time>{
    private final int hour;
    private final int minute;
    private final int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Time that = (Time) obj;
        return this.hour == that.hour && this.minute == that.minute && this.second == that.second;
    }

    @Override
    public int compareTo(Time other) {
        if ((this.hour < other.hour) || (this.hour == other.hour && this.minute < other.minute) ||
                (this.hour == other.hour && this.minute == other.minute && this.second < other.second)) {
            return -1;
        }
        if (this.hour == other.hour && this.minute == other.minute && this.second == other.second) {
            return 0;
        }

        return 1;
    }
}
