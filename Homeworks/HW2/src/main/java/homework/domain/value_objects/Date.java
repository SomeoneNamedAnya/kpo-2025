package homework.domain.value_objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Date implements Comparable<Date>{
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date that = (Date) obj;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }

    @Override
    public int compareTo(Date other) {
        if ((this.year < other.year) || (this.year == other.year && this.month < other.month) ||
                (this.year == other.year && this.month == other.month && this.day < other.day)) {
            return -1;
        }
        if (this.year == other.year && this.month == other.month && this.day == other.day) {
            return 0;
        }

        return 1;
    }
}
