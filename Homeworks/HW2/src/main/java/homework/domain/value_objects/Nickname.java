package homework.domain.value_objects;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Nickname {
    private final String name;

    public Nickname(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nickname that = (Nickname) obj;
        return name.equals(that.name);
    }
}
