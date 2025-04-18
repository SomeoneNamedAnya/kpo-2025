package homework.presentation.controller;

import homework.application.*;
import homework.presentation.request.AnimalRequest;
import homework.domain.entities.Animal;
import homework.domain.entities.Enclosure;
import homework.domain.value_objects.Species;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/animal")
@RequiredArgsConstructor
@Tag(name = "Животные", description = "Управление животными в зоопарке")
public class AnimalController {
    @Autowired
    private AnimalTransferService animalTransferService;
    @Autowired
    private ZooStatisticsService zooStatisticsService;
    @Autowired
    private AnimalTreatService animalTreatService;
    @Autowired
    private AnimalRegistrationService animalRegistrationService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить информации о животном по его ID")
    public ResponseEntity<Animal> getAnimalById(@PathVariable int id) {
        return zooStatisticsService.getAnimal(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/find_enclosure/{id}")
    @Operation(summary = "Получить информации о вольере животного по ID животного")
    public ResponseEntity<Enclosure> getEnclosureByAnimalId(@PathVariable int id) {
        return zooStatisticsService.findEnclosureByAnimalId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить животное по его ID")
    public ResponseEntity<Boolean> deleteByAnimalId(@PathVariable int id) {
        return ResponseEntity.ok(animalRegistrationService.deleteAnimal(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получить информации о всех животных")
    public List<Animal> getAllAnimal() {
        return zooStatisticsService.getAllAnimal();
    }

    @PostMapping("/create")
    @Operation(summary = "Добавить животное")
    public ResponseEntity<Animal> createAnimal(
            @Valid @RequestBody AnimalRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        var speciesOptional = Species.find(request.species());
        Species species;
        if (speciesOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No this type");
        } else {
            species = speciesOptional.get();
        }
        String[] parts = request.dateOfBirth().split("[/]");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(request.dateOfBirth(), formatter);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
        int day   = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year  = Integer.parseInt(parts[2]);

        var animal = animalRegistrationService.createAnimal(request.animalType(),
                species, request.nickname(), year, month, day, request.sex(),
                request.food(), request.health());

        return ResponseEntity.status(HttpStatus.CREATED).body(animal);
    }

    @PatchMapping("/transfer/{animalId}/{enclosureId}")
    @Operation(summary = "Поселить животное с animalId в вольер с enclosureId")
    public ResponseEntity<Boolean> transferAnimalById(@PathVariable int animalId,
                                      @PathVariable int enclosureId) {

        return ResponseEntity.ok(animalTransferService.placed(animalId, enclosureId));
    }


    @PatchMapping("/treat/{animalId}")
    @Operation(summary = "Вылечить животное с animalId")
    public ResponseEntity<Boolean> treatAnimaById(@PathVariable int animalId) {

        return ResponseEntity.ok(animalTreatService.treatById(animalId));
    }


}