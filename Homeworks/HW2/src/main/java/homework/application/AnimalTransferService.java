package homework.application;

import homework.application.interfaces.IHouseholdEventRepository;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.application.interfaces.IAnimalRepository;
import homework.application.interfaces.IEnclosureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnimalTransferService {

    @Autowired
    private final IAnimalRepository animalRepository;
    @Autowired
    private final IEnclosureRepository enclosureRepository;

    @Autowired
    private final IHouseholdEventRepository householdEventRepository;


    public boolean placed(int animalId, int enclosureId) {
        Enclosure to = enclosureRepository.findById(enclosureId).orElse(null);
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (to == null || animal == null){
            return false;
        }

        if (!to.isAcceptable(animal)) {
            return false;
        }

        var curEnclosure = enclosureRepository.getAll().stream().filter(cur -> cur.hasAnimal(animal)).findFirst();
        curEnclosure.ifPresent(enclosure -> householdEventRepository.save(enclosure.delAnimal(animal)));

        householdEventRepository.save(to.pushAnimal(animal));

        return true;
    }
    public boolean moveOut(int animalId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);

        if (animal == null){
            return false;
        }
        var curEnclosure = enclosureRepository.getAll().stream().filter(cur -> cur.hasAnimal(animal)).findFirst();
        curEnclosure.ifPresent(enclosure -> householdEventRepository.save(enclosure.delAnimal(animal)));

        return true;
    }
}
