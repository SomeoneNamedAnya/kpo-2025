package homework.app.Zoo;
import homework.app.Animal.Animal;
import homework.app.Interface.IContact;
import homework.app.Vet.Vet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Zoo {
    @Autowired
    private final Vet vetClinic;
    private final ArrayList<Animal> listAnimals = new ArrayList<Animal>();
    @Autowired
    public Zoo(Vet vetClinic) {
        this.vetClinic = vetClinic;
    }

    public boolean addAnimal(Animal animal) {
        if (vetClinic.medicalExamination(animal)) {
            listAnimals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    public void makeAnimalReport() {
        int n = listAnimals.size();
        System.out.printf("Number of animals: %d\n", listAnimals.size());
        System.out.println("-------------------------");
        int total_food = 0;
        for (int i = 0; i < n; i++) {
            listAnimals.get(i).printInfo(i + 1);
            total_food += listAnimals.get(i).getWeightFood();
        }
        System.out.println("-------------------------");
        System.out.printf("Total amount of food (kg): %d\n", total_food);
    }

    public void makeHerboList() {
        int total = (int) listAnimals.stream()
                .filter(IContact::isContact)
                .count();

        System.out.printf("Total: %d\n", total);
        System.out.println("-------------------------");
        for (int i = 0, j = 1; i < listAnimals.size(); i++) {
            if (listAnimals.get(i).isContact()) {
                listAnimals.get(i).printInfo(j);
                j += 1;
            }
        }

    }

}
