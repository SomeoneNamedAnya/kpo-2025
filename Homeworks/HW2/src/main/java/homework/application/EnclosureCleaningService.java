package homework.application;

import homework.domain.entities.Enclosure;
import homework.infrastructure.EnclosureRepository;
import homework.infrastructure.HouseholdEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EnclosureCleaningService {
    @Autowired
    private EnclosureRepository enclosureRepository;
    @Autowired
    private final HouseholdEventRepository householdEventRepository;

    public boolean cleanEnclosureById(int enclosureId) {
        Enclosure enclosure = enclosureRepository.findById(enclosureId).orElse(null);
        if (enclosure == null) {
            return false;
        }
        householdEventRepository.save(enclosure.clean());
        return true;
    }
}
