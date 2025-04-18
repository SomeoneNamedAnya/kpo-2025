package homework.application.interfaces;

import homework.domain.entities.FeedingSchedule;

import java.util.List;
import java.util.Optional;

public interface IFeedingScheduleRepository {
    Optional<FeedingSchedule> findById(int id);

    boolean deleteById(int id);
    boolean save(FeedingSchedule animal);

    List<FeedingSchedule> getAll();

}
