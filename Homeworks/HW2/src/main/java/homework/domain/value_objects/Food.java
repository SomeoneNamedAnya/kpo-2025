package homework.domain.value_objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Food {
    private final String type;

    public Food(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Food that = (Food) obj;
        return type.equals(that.type);
    }
}
