package homework.application;

import homework.domain.entities.Animal;
import homework.domain.entities.FeedingSchedule;
import homework.domain.value_objects.Time;
import homework.application.interfaces.IAnimalRepository;
import homework.application.interfaces.IFeedEventRepository;
import homework.application.interfaces.IFeedingScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FeedingOrganizationService {
    int cntFeedingSchedule = 0;
    @Autowired
    private final IAnimalRepository animalRepository;
    @Autowired
    private final IFeedingScheduleRepository feedingScheduleRepository;
    @Autowired
    private final IFeedEventRepository feedEventRepository;

    public FeedingSchedule makeNewTime(int animalId, Time time) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return null;
        }

        if (feedingScheduleRepository.getAll().stream()
                .anyMatch(temp -> temp.getTime() == time && temp.getAnimal() == animal)) {
            return null;
        }
        cntFeedingSchedule += 1;
        var temp = new FeedingSchedule(cntFeedingSchedule, animal, time, animal.getFood());
        feedingScheduleRepository.save(temp);

        return temp;

    }

    public boolean feedAll() {
        feedingScheduleRepository.getAll().stream()
                .sorted(Comparator.comparing(FeedingSchedule::getTime))
                .map(FeedingSchedule::make)
                .filter(Optional::isPresent)
                .forEach(res -> feedEventRepository.save(res.get()));
        return true;
    }
    public boolean doSkip(int feedingScheduleId) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingScheduleId).orElse(null);

        if (feedingSchedule == null) {
            return false;
        }

        return feedingSchedule.doSkip();

    }

    public boolean doAllSkip() {

        feedingScheduleRepository.getAll().forEach(FeedingSchedule::doSkip);

        return true;

    }
    public boolean undoAllSkip() {

        feedingScheduleRepository.getAll().forEach(FeedingSchedule::undoSkip);

        return true;

    }
    public boolean deleteById(int id) {

        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(id).orElse(null);

        if (feedingSchedule == null) {
            return false;
        }
        feedingScheduleRepository.deleteById(id);
        return true;
    }
    public boolean undoSkip(int feedingScheduleId) {
        FeedingSchedule feedingSchedule = feedingScheduleRepository.findById(feedingScheduleId).orElse(null);

        if (feedingSchedule == null) {
            return false;
        }

        return feedingSchedule.undoSkip();
    }

    public boolean doAllSkipForAnimal(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);
        if (animal == null) {
            return false;
        }

        feedingScheduleRepository.getAll().stream()
                .filter(cur -> cur.getAnimal() == animal)
                .forEach(FeedingSchedule::doSkip);
        return true;
    }

    public boolean undoAllSkipForAnimal(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);
        if (animal == null) {
            return false;
        }

        feedingScheduleRepository.getAll().stream()
                .filter(cur -> cur.getAnimal() == animal)
                .forEach(FeedingSchedule::undoSkip);
        return true;
    }



}
