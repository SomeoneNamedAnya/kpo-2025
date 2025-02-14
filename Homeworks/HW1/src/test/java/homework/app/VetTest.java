package homework.app;

import homework.app.Animal.Animal;
import homework.app.Animal.Wolf;
import homework.app.Vet.Vet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;
@SpringBootTest
public class VetTest {
    @Autowired
    private Vet vet;

    @Mock
    Animal acceptAnimal;

    @Mock
    Animal declineAnimal;

    @Test
    @DisplayName("Тест, когда животному должны выдать результат не болен")
    void medicalExaminationTrueTest() {
        when(acceptAnimal.getHealth()).thenReturn(100);
        Assertions.assertTrue(vet.medicalExamination(acceptAnimal));
    }
    @Test
    @DisplayName("Тест, когда животному должны выдать результат болен")
    void medicalExaminationFalseTest() {
        when(declineAnimal.getHealth()).thenReturn(0);
        Assertions.assertFalse(vet.medicalExamination(declineAnimal));
    }

    @Test
    @DisplayName("Тест, когда животному должны присвоить здоровье")
    void medicalExaminationGetTest() {
        Animal animal = new Wolf("1", 1,1);
        vet.medicalExamination(animal);
        Assertions.assertTrue(animal.getHealth() >= 0);
    }

}
