package homework.application;
import homework.application.interfaces.*;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.entities.FeedingSchedule;
import homework.domain.events.IEvent;
import homework.domain.value_objects.Species;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ZooStatisticsService {
    @Autowired
    private final IAnimalRepository animalRepository;
    @Autowired
    private final IEnclosureRepository enclosureRepository;
    @Autowired
    private final IFeedingScheduleRepository feedingScheduleRepository;
    @Autowired
    private final IFeedEventRepository feedEventRepository;
    @Autowired
    private final IHouseholdEventRepository householdEventRepository;

    public List<FeedingSchedule> getAnimalFeedingSchedule(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return null;
        }
        return feedingScheduleRepository.getAll().stream()
                .filter(cur -> cur.getAnimal().equals(animal))
                .sorted(Comparator.comparing(FeedingSchedule::getTime)).toList();
    }

    public int getCntOfAllAnimal() {
        return animalRepository.getAll().size();
    }
    public int getCntOfAllEnclosure() {
        return enclosureRepository.getAll().size();
    }
    public int getCntOfAllFeedingSchedule() {
        return feedingScheduleRepository.getAll().size();
    }

    public List<Animal> getAllAnimal() {
        return animalRepository.getAll();
    }
    public Optional<Animal> getAnimal(int animalId) {
        return animalRepository.findById(animalId);
    }

    public List<Enclosure> getAllEnclosure() {
        return enclosureRepository.getAll();
    }
    public Optional<Enclosure> getEnclosure(int enclosureId) {
        return enclosureRepository.findById(enclosureId);
    }

    public List<FeedingSchedule> getAllFeedingSchedule() {
        return feedingScheduleRepository.getAll();
    }
    public Optional<FeedingSchedule> getFeedingSchedule(int feedingScheduleId) {
        return feedingScheduleRepository.findById(feedingScheduleId);
    }

    public Optional<Enclosure> findEnclosureByAnimalId(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return Optional.empty();
        }

        return enclosureRepository.getAll().stream().filter(cur -> cur.hasAnimal(animal)).findFirst();
    }

    public List<IEvent> getHistoryOfFeed() {
        return feedEventRepository.getAll();
    }

    public List<IEvent> getHistoryOfZooEvent() {
        return householdEventRepository.getAll();
    }

    public List<Enclosure> getFilterEnclosure(Species species, int minCnt) {
        return enclosureRepository.getAll().stream().filter(cur -> cur.getSpecies().equals(species) &&
                cur.getMaxAnimals() - cur.getCurAnimals().size() >= minCnt).toList();
    }
}
