package homework.application.interfaces;

import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;

import java.util.List;
import java.util.Optional;

public interface IAnimalRepository {
    Optional<Animal> findById(int id);

    boolean deleteById(int id);
    boolean save(Animal animal);
    List<Animal> getAll();
}
