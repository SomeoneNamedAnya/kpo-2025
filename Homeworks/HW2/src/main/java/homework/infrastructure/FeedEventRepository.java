package homework.infrastructure;

import homework.domain.events.IEvent;
import homework.application.interfaces.IFeedEventRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedEventRepository implements IFeedEventRepository {
    private List<IEvent> eventList = new ArrayList<>();

    @Override
    public boolean save(IEvent enclosure) {

        eventList.add(enclosure);

        return true;
    }

    @Override
    public List<IEvent> getAll() {
        return eventList;
    }
}
