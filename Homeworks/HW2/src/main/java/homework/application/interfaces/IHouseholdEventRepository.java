package homework.application.interfaces;

import homework.domain.events.IEvent;

import java.util.List;

public interface IHouseholdEventRepository {
    boolean save(IEvent animal);
    List<IEvent> getAll();
}
