package homework.app.Vet;
import homework.app.Interface.IAlive;
import org.springframework.stereotype.Component;

@Component
public class Vet {
    public Vet() {}
    public boolean medicalExamination(IAlive newEntity) {
        return  newEntity.getAge() < newEntity.getMaxAge() / 2;
    }
}
