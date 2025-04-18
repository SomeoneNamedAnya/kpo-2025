package homework.infrastructure;

import homework.domain.entities.Animal;
import homework.application.interfaces.IAnimalRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class AnimalRepository implements IAnimalRepository {
    private List<Animal> animalList = new ArrayList<>();
    @Override
    public Optional<Animal> findById(int id) {
        return animalList.stream().filter(enclosure -> enclosure.getId() == id).findFirst();
    }

    @Override
    public boolean deleteById(int id) {
        Optional<Animal> curAnimal = this.findById(id);
        if (curAnimal.isEmpty()) {
            return false;
        }
        animalList.remove(curAnimal.get());
        return true;
    }


    @Override
    public boolean save(Animal animal) {
        animalList.add(animal);
        return true;
    }

    @Override
    public List<Animal> getAll() {
        return animalList;
    }
}
