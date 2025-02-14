package homework.app.Vet;
import homework.app.Interface.IAlive;
import org.springframework.stereotype.Component;

@Component
public class Vet {
    public Vet() {}
    public boolean medicalExamination(IAlive newEntity) {
        if (newEntity.getHealth() == -1) {
            newEntity.setHealth((int)(Math.random() * 100));
        }
        return newEntity.getHealth() >= 10;
    }
}
