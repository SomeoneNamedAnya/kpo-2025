package homework.application;

import homework.domain.entities.Animal;
import homework.infrastructure.AnimalRepository;
import homework.infrastructure.HouseholdEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnimalTreatService {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private final HouseholdEventRepository householdEventRepository;

    public boolean treatById(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return false;
        }

        householdEventRepository.save(animal.treat());
        return true;
    }
}
