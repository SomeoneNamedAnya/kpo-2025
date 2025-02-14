package homework.app.Vet;
import homework.app.Interface.IAlive;
import org.springframework.stereotype.Component;

/**
 * Класс ветклиники
 */
@Component
public class Vet {
    public Vet() {}

    /**
     * Проверка здоровья животного (если у жевотного нет информации о его здоровье
     * то присваивается рандомное число от 0 до 100 - процент здоровья,
     * далее если процент здоровья > 10 то животное получает одобрение от ветклиники иначе
     * получает отказ) Есть вероятность что при заполнени данных о животных можно получить отказ
     * @param newEntity - сущьность для проверки здоровья
     * @return true - здоров / false - болен
     */
    public boolean medicalExamination(IAlive newEntity) {
        if (newEntity.getHealth() == -1) {
            newEntity.setHealth((int)(Math.random() * 100));
        }
        return newEntity.getHealth() >= 10;
    }
}
