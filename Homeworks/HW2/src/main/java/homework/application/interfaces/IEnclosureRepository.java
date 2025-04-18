package homework.application.interfaces;

import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.entities.FeedingSchedule;

import java.util.List;
import java.util.Optional;

public interface IEnclosureRepository {
    Optional<Enclosure> findById(int id);

    boolean deleteById(int id);
    boolean save(Enclosure enclosure);
    List<Enclosure> getAll();
}
