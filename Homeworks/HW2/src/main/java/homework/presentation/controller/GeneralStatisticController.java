package homework.presentation.controller;

import homework.application.ZooStatisticsService;
import homework.domain.entities.Enclosure;
import homework.domain.events.IEvent;
import homework.domain.value_objects.Species;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
@Tag(name = "Статистика", description = "Получение общей статистики зоопарка")
public class GeneralStatisticController {
    @Autowired
    private ZooStatisticsService zooStatisticsService;

    @GetMapping("/feeding_history")
    @Operation(summary = "Получить информации о всех событиях кормления")
    public List<IEvent> getAllFeedingEvent() {
        return zooStatisticsService.getHistoryOfFeed();
    }

    @GetMapping("/event_history")
    @Operation(summary = "Получить информации о всех событиях в зоопарке за исключением кормления")
    public List<IEvent> getAllEvent() {
        return zooStatisticsService.getHistoryOfZooEvent();
    }

    @GetMapping("/count/animal/all")
    @Operation(summary = "Получить количество всех животных в зоопарке")
    public ResponseEntity<Integer> getCntOfAllAnimal() {
        return ResponseEntity.ok(zooStatisticsService.getCntOfAllAnimal());
    }
    @GetMapping("/count/enclosure/all")
    @Operation(summary = "Получить количество всех вольеров в зоопарке")
    public ResponseEntity<Integer> getCntOfAllEnclosure() {
        return ResponseEntity.ok(zooStatisticsService.getCntOfAllEnclosure());
    }

    @GetMapping("/count/feeding_schedule/all")
    @Operation(summary = "Получить количество всех расписаниях кормления в зоопарке")
    public ResponseEntity<Integer> getCntOfAllFeedingSchedule() {
        return ResponseEntity.ok(zooStatisticsService.getCntOfAllFeedingSchedule());
    }

    @GetMapping("/enclosure/{type}/{cnt}")
    @Operation(summary = "Получить все вольеры предназначенные для type, где есть хотябы cnt свободных мест")
    public List<Enclosure> getFilterEnclosure(@PathVariable String type,
                                              @PathVariable int cnt) {
        var speciesOptional = Species.find(type);
        Species species;
        if (speciesOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No this type");
        } else {
            species = speciesOptional.get();
        }

        if (cnt < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "cnt has to be grater than zero or equal zero");
        }

        return zooStatisticsService.getFilterEnclosure(species, cnt);
    }



}