package homework.infrastructure;

import homework.domain.entities.FeedingSchedule;
import homework.application.interfaces.IFeedingScheduleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class FeedingScheduleRepository implements IFeedingScheduleRepository {
    private List<FeedingSchedule> feedingScheduleList = new ArrayList<>();


    @Override
    public Optional<FeedingSchedule> findById(int id) {
        return feedingScheduleList.stream().filter(feedingSchedule -> feedingSchedule.getId() == id).findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        Optional<FeedingSchedule> curFeedingSchedule = this.findById(id);
        if (curFeedingSchedule.isEmpty()) {
            return false;
        }
        feedingScheduleList.remove(curFeedingSchedule.get());
        return true;
    }

    @Override
    public boolean save(FeedingSchedule feedingSchedule) {
        feedingScheduleList.add(feedingSchedule);
        return true;
    }

    @Override
    public List<FeedingSchedule> getAll() {
        return feedingScheduleList;
    }
}
