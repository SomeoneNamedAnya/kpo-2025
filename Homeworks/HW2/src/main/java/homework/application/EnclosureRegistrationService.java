package homework.application;

import homework.application.interfaces.IHouseholdEventRepository;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.value_objects.Species;
import homework.application.interfaces.IEnclosureRepository;

import java.util.ArrayList;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnclosureRegistrationService {
    int cntIdEnclosure = 0;

    @Autowired
    private final IEnclosureRepository enclosureRepository;

    @Autowired
    private final IHouseholdEventRepository householdEventRepository;

    public Enclosure createEnclosure(Species species, int size, int maxAnimal) {
        cntIdEnclosure += 1;
        var enclosure = new Enclosure(cntIdEnclosure, species, size, maxAnimal);
        enclosureRepository.save(enclosure);
        return enclosure;
    }

    public boolean deleteEnclosure(int id) {
        var opt =  enclosureRepository.findById(id);
        if (opt.isEmpty()) {
            return false;
        } else {
            Enclosure enclosure = opt.get();
            List<Animal> temp =  new ArrayList<>(enclosure.getCurAnimals());
            temp.forEach(cur -> householdEventRepository.save(enclosure.delAnimal(cur)));
        }

        return enclosureRepository.deleteById(id);
    }

    public List<Animal> getAnimals(int id) {
        var enclosure = enclosureRepository.findById(id);
        if (enclosure.isEmpty()) {
            return Collections.emptyList();
        }
        return enclosure.get().getCurAnimals();
    }

}
