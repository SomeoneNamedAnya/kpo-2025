package homework.application.interfaces;

import homework.domain.entities.Animal;
import homework.domain.events.IEvent;

import java.util.List;
import java.util.Optional;

public interface IFeedEventRepository {
    boolean save(IEvent animal);
    List<IEvent> getAll();
}
