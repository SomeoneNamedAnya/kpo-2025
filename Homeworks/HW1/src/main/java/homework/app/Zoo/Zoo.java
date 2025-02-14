package homework.app.Zoo;
import homework.app.Animal.Animal;
import homework.app.Interface.IContact;
import homework.app.Interface.IInfo;
import homework.app.Inventory.Thing;
import homework.app.Vet.Vet;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
public class Zoo {
    private int curInvNum = 1;
    @Getter
    private final ArrayList<Animal> listAnimals = new ArrayList<Animal>();
    @Getter
    private final ArrayList<Thing> listThing = new ArrayList<Thing>();
    @Autowired
    private final Vet vetClinic;
    @Autowired
    public Zoo(Vet vetClinic) {
        this.vetClinic = vetClinic;
    }
    public boolean addAnimal(Animal animal) {
        if (vetClinic.medicalExamination(animal)) {
            animal.setInvNumber(curInvNum);
            curInvNum += 1;
            listAnimals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    public void makeAnimalFoodReport() {
        System.out.printf("Total: %d\n", listAnimals.size());
        System.out.println("-------------------------");

        AtomicInteger count = new AtomicInteger(1);
        AtomicInteger totalFood = new AtomicInteger();

        IntStream.range(0, listAnimals.size()).forEach(i -> {
            listAnimals.get(i).printInfo(count.get());
            totalFood.addAndGet(listAnimals.get(i).getFood());
            count.getAndIncrement();
        });

        System.out.println("-------------------------");
        System.out.printf("Total amount of food (kg): %d\n", totalFood.get());
    }

    public void makeContactAnimalReport() {
        System.out.printf("Total: %d\n", (int) listAnimals.stream().filter(IContact::isContact).count());
        System.out.println("-------------------------");

        AtomicInteger count = new AtomicInteger(1);
        IntStream.range(0, listAnimals.size())
                .filter(i -> listAnimals.get(i).isContact())
                .forEach(i -> listAnimals.get(i).printInfo(count.getAndIncrement()));
    }

    public void makeGeneralReport() {
        System.out.println("Animals");
        System.out.printf("Total: %d\n", listAnimals.size());
        System.out.println("-------------------------");

        AtomicInteger count = new AtomicInteger(1);
        IntStream.range(0, listAnimals.size())
                .forEach(i -> listAnimals.get(i).printInfo(count.getAndIncrement()));

        System.out.println("Things");
        System.out.printf("Total: %d\n", listThing.size());
        System.out.println("-------------------------");

        count.set(1);
        IntStream.range(0, listThing.size())
                .forEach(i -> listThing.get(i).printInfo(count.getAndIncrement()));

    }


}
