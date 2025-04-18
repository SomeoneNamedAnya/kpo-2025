package homework.application;

import homework.application.interfaces.IHouseholdEventRepository;
import homework.domain.entities.Animal;
import homework.domain.value_objects.*;
import homework.application.interfaces.IAnimalRepository;
import homework.application.interfaces.IEnclosureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnimalRegistrationService {

    int cntIdAnimal = 0;

    @Autowired
    private final IAnimalRepository animalRepository;
    @Autowired
    private final IEnclosureRepository enclosureRepository;
    @Autowired
    private final IHouseholdEventRepository householdEventRepository;

    public Animal createAnimal(String animalType, Species species,
                               String nickname, int year, int month, int day,
                               boolean sex, String food, boolean health) {
        cntIdAnimal += 1;
        var animal = new Animal(cntIdAnimal, new AnimalType(animalType, species),
                new Nickname(nickname), new Date(year, month, day),
                sex, new Food(food), health);
        animalRepository.save(animal);
        return animal;
    }

    public boolean deleteAnimal(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return false;
        }

        var curEnclosure = enclosureRepository.getAll().stream().filter(cur -> cur.hasAnimal(animal)).findFirst();
        curEnclosure.ifPresent(enclosure -> householdEventRepository.save(enclosure.delAnimal(animal)));

        animalRepository.deleteById(animalId);
        return true;
    }
}
