package homework.presentation.controller;

import homework.application.*;
import homework.presentation.request.EnclosureRequest;
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

import java.util.List;

@RestController
@RequestMapping("/api/enclosure")
@RequiredArgsConstructor
@Tag(name = "Вольеры", description = "Управление вольерами в зоопарке")
public class EnclosureController {
    @Autowired
    private ZooStatisticsService zooStatisticsService;
    @Autowired
    private EnclosureCleaningService enclosureCleaningService;
    @Autowired
    private EnclosureRegistrationService enclosureRegistrationService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить информации о вольере по его ID")
    public ResponseEntity<Enclosure> getEnclosureById(@PathVariable int id) {
        return zooStatisticsService.getEnclosure(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    @Operation(summary = "Получить информации о всех вольерах")
    public List<Enclosure> getAllEnclosure() {
        return zooStatisticsService.getAllEnclosure();
    }
    @PostMapping("/create")
    @Operation(summary = "Добавить новый вольер")
    public ResponseEntity<Enclosure> createEnclosure(
            @Valid @RequestBody EnclosureRequest request,
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


        var enclosure = enclosureRegistrationService.createEnclosure(species,
                request.size(), request.maxAnimals());

        return ResponseEntity.status(HttpStatus.CREATED).body(enclosure);
    }

    @GetMapping("/clean/{enclosureId}")
    @Operation(summary = "Убраться в вольере c enclosureId")
    public ResponseEntity<Boolean> cleanEnclosureById(@PathVariable int enclosureId) {

        return ResponseEntity.ok(enclosureCleaningService.cleanEnclosureById(enclosureId));
    }

    @DeleteMapping("/delete/{enclosureId}")
    @Operation(summary = "Удалить вольер c enclosureId")
    public ResponseEntity<Boolean> deleteEnclosureById(@PathVariable int enclosureId) {

        return ResponseEntity.ok(enclosureRegistrationService.deleteEnclosure(enclosureId));
    }

    @GetMapping("/animals/{enclosureId}")
    @Operation(summary = "Вывести всех животных содержащихся в вольере с enclosureId")
    public List<Animal> getAllAnimalsInEnclosureById(@PathVariable int enclosureId) {

        return enclosureRegistrationService.getAnimals(enclosureId);
    }



}