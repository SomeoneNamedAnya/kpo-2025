package homework.infrastructure;

import homework.domain.entities.Enclosure;
import homework.application.interfaces.IEnclosureRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
@Getter
@Setter
public class EnclosureRepository implements IEnclosureRepository {
    private List<Enclosure> enclosureList = new ArrayList<>();
    @Override
    public Optional<Enclosure> findById(int id) {
        return enclosureList.stream().filter(enclosure -> enclosure.getId() == id).findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        Optional<Enclosure> curEnclosure = this.findById(id);
        if (curEnclosure.isEmpty()) {
            return false;
        }
        enclosureList.remove(curEnclosure.get());
        return true;
    }

    @Override
    public boolean save(Enclosure enclosure) {

        enclosureList.add(enclosure);

        return true;
    }

    @Override
    public List<Enclosure> getAll() {
        return enclosureList;
    }
}
